;include e_mcr.txt

sseg segment para stack 'stack'
	db 64 dup ( 'STACK' )
sseg ends

dseg segment para public 'data'
	input_error_message db 0dh, 0ah, 'the number you entered is too large$'
	min_error_message db 0dh, 0ah, 'array has to have at least 2 elements $'
	max_error_message db 0dh, 0ah, 'array can`t have more than 20 elements $'
    wrong_character_message db 0dh, 0ah, 'wrong character$'
    empty_input_message db 0dh, 0ah, 'empty input$'
	enter_element_message db 0dh, 0ah, 'enter element: $'
	enter_array_length_message db 0dh, 0ah, 'enter array length (2-20): $'
	space                   db ' $'
	array_elements_message db 0dh, 0ah, 'array elements: $'
	sum_message db 0dh, 0ah, 'sum of array elements: $'
	sorted_message db 0dh, 0ah, 'sorted $'
	overflow_error db 0dh, 0ah, 'Overflow $'
	min_element_message db 0dh, 0ah, 'Min: $'
	max_element_message db 0dh, 0ah, 'Max: $'
	
	array dw 20 dup(0)
	sizeArr dw 0
	num dw 0
	temp dw 0
	is_negative dw 0
	minArrayLength dw 2
	maxArrayLength dw 20
	min_element dw 0
	max_element dw 0
dseg ends


cseg segment para public 'code'

exit macro
	mov ah, 4ch  ;terminate program with return code
	int 21h
endm

print macro string
	push ax
	lea dx,string
	mov ah,09h
	int 21h
	pop ax
endm

sum_of_array macro arr
local m_sum
    mov cx, sizeArr
    xor ax, ax
    xor bx, bx
	xor si, si
    m_sum:
      mov ax, arr[si]
      add bx, ax
      jo overflow
      add si, 2
      loop m_sum
    mov ax, bx
	print sum_message
    printNum ax
    ret
	overflow:
	print overflow_error
	 exit
endm

printNum macro numb
local m1, m2, m3
	mov ax, numb   ; move variable to ax
    mov bx, ax
    or bx,bx
    jns m1
    mov al,'-'
    int 29h
    neg bx

    m1:
      xor cx ,cx
      mov ax, bx
      mov bx, 10

    m2:
      xor dx, dx
      div bx
      add dl,'0'
      push dx
      inc cx
      test ax, ax
      jnz m2

    m3:
      pop ax
      int 29h
      loop m3

   ; exit
endm
  

    main proc
    assume cs: cseg, ds: dseg, ss: sseg
	
    mov ax, dseg
    mov ds, ax
	
	call enter_array
	call print_array
	
	call find_max_element
	call sort
	call print_array
	main endp
	
exit proc
	mov ax, 4c00h
    int 21h
	ret
exit endp

find_max_element proc
	sum_of_array array     ;calling sum macros
	xor si,si
	xor dx,dx
	mov dx, array[si]
	mov max_element, dx     ; element array[0]
	mov cx, sizeArr
	
	repeatl:
	mov ax, array[si]
	cmp max_element, ax
	jg no_change
	mov max_element, ax
	no_change:
	add si, 2
	loop repeatl
print max_element_message
mov ax, max_element
printNum ax
ret
find_max_element endp
  
sort proc
	push ax
	push bx
	push cx
	push dx
	push di
	
	xor si,si
	lea si, array
	xor bx,bx
	mov bx, sizeArr
	
	mov ax, si
	mov cx, bx
	dec cx
	
	outer_loop:
	mov bx,cx
	mov si,ax
	mov di,ax
	
	add di,2
	inner_loop:
	mov dx,[si]
	cmp dx, [di]
	jl skip_swap
	xchg dx,[di]
	mov [si],dx
	
	skip_swap:
	add si,2
	add di,2
	
	dec bx
	jnz inner_loop
	loop outer_loop
	print sorted_message
	pop di
	pop dx
	pop cx
	pop bx
	pop ax
	ret
sort endp  
  

print_array proc
  
  print array_elements_message
    mov cx, sizeArr
    xor si, si
    display_loop:
      mov ax, array[si]
      push cx
	  push ax
      lea dx, space
	  mov ah,09h
	  int 21h
	  pop ax
      printNum ax
      pop cx
      add si, 2
      loop display_loop
    ret
print_array endp
  
check_array_length proc
	xor ax,ax
	mov ax,sizeArr
	cmp ax, minArrayLength
	jl min_error
	cmp ax, maxArrayLength
	jg max_error
	ret
	
	min_error:
	lea dx, min_error_message
    mov ah, 09h
    int 21h
     exit
	
	max_error:
	lea dx, max_error_message
    mov ah, 09h
    int 21h
     exit
check_array_length endp

enter_array proc
    print enter_array_length_message
    call read_digit
	mov ax, num
    mov sizeArr, ax
	call check_array_length
	
    mov cx, ax
    xor bx, bx
	lea si, array
    read_array_loop:
      push cx
      print enter_element_message
      call read_digit
	  mov ax, num
	  mov [si], ax
	  add si, 2
      pop cx
      loop read_array_loop
    ret
enter_array endp

read_digit proc
	mov num,0
    mov bx, 10
    mov cx, 5
    mov is_negative,0
	
    read:
    xor ax, ax
    mov ah, 01h   ;Reads a character from the standard input device and echoes it to the standard output device.
    int 21h
    
    cmp al, 13
    je stop
    cmp al, 48
    jl check_sign
    cmp al, 57
    ja wrong_character
    
    sub al, '0'
    sub ah, ah
    mov temp, ax
    
    mov ax, num
    mul bx
    jo input_error
   
   
    cmp ax, 32767
    ja input_error
  
    add ax, temp
    jo input_error
    mov num, ax
    loop read
    
    cmp is_negative, 1
    je make_negative
    ret
    
    stop:
    cmp cx, 5
    je empty_input
    mov cx, 0
    cmp is_negative, 1
    je make_negative
    ret
    
    make_negative:
    neg num
    ret
    
    check_sign:
    cmp al, '-'
    jne wrong_character
    cmp cx, 5
    jne wrong_character
    mov is_negative, 1
    jmp read
    
    input_error:
    print input_error_message
    
    mov ah, 4ch
    int 21h
    
    wrong_character:
    print wrong_character_message
    
    mov ah, 4ch
    int 21h
    
    empty_input:
    print empty_input_message
    mov ah, 4ch
    int 21h
read_digit endp
  
cseg ends 
end main
