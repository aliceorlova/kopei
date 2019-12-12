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
	enter_element_message db 0dh, 0ah, 'enter $'
	enter_row_number_message db 0dh, 0ah, 'enter number of rows (2-20): $'
	enter_column_number_message db 0dh, 0ah, 'enter number of columns(2-20): $'
	number_to_find_message db 0dh, 0ah, 'enter element to find: $'
	space                   db ' $'
	array_elements_message db 0dh, 0ah, 'array elements: $'
	coordinates_message db 0dh, 0ah, 'Coordinates: $'
	overflow_error db 0dh, 0ah, 'Overflow $'
	no_element_found_message db 0dh, 0ah, 'No such element was found. $'
	i dw 1
	j dw 1
	array dw 400 dup(0)
	sizeArr dw 0
	rowNumber dw 0
	columnNumber dw 0
	numberToFind dw 0
	num dw 0
	temp dw 0
	is_negative dw 0
	minArrayLength dw 2
	maxArrayLength dw 20
	min_element dw 0
	max_element dw 0
dseg ends


cseg segment para public 'code'

print macro string
	push ax
	lea dx,string
	mov ah,09h
	int 21h
	pop ax
endm
    main proc
    assume cs: cseg, ds: dseg, ss: sseg
	
    mov ax, dseg
    mov ds, ax
	
	call enter_size
	call read_array
	call find_number
	call exit
	main endp
	
exit proc
	mov ax, 4c00h
    int 21h
	ret
exit endp

  
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
    call exit
	
	max_error:
	lea dx, max_error_message
    mov ah, 09h
    int 21h
    call exit
check_array_length endp

enter_size proc
	xor cx, cx
	xor si, si
	print enter_row_number_message
	xor ax, ax
	call read_digit
	mov ax, num
	mov rowNumber, ax
	mov sizeArr, ax
	call check_array_length
	
	print enter_column_number_message
	xor ax, ax
	call read_digit
	mov ax, num
	mov columnNumber, ax
	mov sizeArr, ax
	call check_array_length
	
	ret
enter_size endp

read_array proc
	xor ax,ax
	xor cx,cx
	xor bx,bx
	xor si,si
	mov cx, rowNumber
	loop1:
		push cx
		mov cx, columnNumber
		mov ax, 1
		mov j, ax
		loop2:
			push cx
			push bx
			print enter_element_message
			mov al,'['
			int 29h
			xor ax,ax
			mov ax, i
			call printNum
			
			mov al,','
			int 29h
			xor ax,ax
			mov ax,j
			call printNum
			
			mov al,']'
			int 29h
			mov al, ':'
			int 29h
			call read_digit
			xor ax,ax
			mov ax,num
			inc j 
			pop bx
			mov array[bx], ax
			add bx,2
			pop cx
			loop loop2
			inc i 
			pop cx
		loop loop1
		ret
read_array endp


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
      call printNum
      pop cx
      add si, 2
      loop display_loop
    ret
print_array endp

printNum proc
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

    ret
  printNum endp
  
  
find_number proc
	mov i, 1
	mov j, 1
	print number_to_find_message
	call read_digit
	xor ax,ax
	mov ax,num
	mov numberToFind, ax
	xor ax, ax
	xor dx, dx
	xor di, di
	xor si, si
	mov cx, rowNumber
	mov dx, numberToFind
loop_for_i:
	push cx
	mov cx, columnNumber
	mov ax, 1
	mov j, ax
	loop_for_j:
		push cx
		cmp array[si], dx  ;comparing current element with the needed one
		je number_found
		jne search_further
		number_found:
			inc di
			push ax
			print coordinates_message
			mov al, '['
			int 29h
			xor ax, ax
			mov ax, i      ; first time it's 1
			call printNum
			mov al, ','
			int 29h
			xor ax, ax
			mov ax, j
			call printNum
			mov al, ']'
			int 29h
			pop ax
			jmp search_further
		search_further:
			add si, 2
			inc j
			pop cx
			loop loop_for_j
			inc i
			pop cx
			loop loop_for_i
		cmp di, 0
		je no_element_found
		ret
	no_element_found:
		print no_element_found_message
		call exit
find_number endp

  
cseg ends 
end main
