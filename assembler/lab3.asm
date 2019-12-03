sseg segment para stack 'stack'; sseg - segment name, segment - reserved word
;para-alignment type(paragraph),stack-for any future segment linking,'stack' - name for linking
    db 64 dup ( 'STACK' )
sseg ends

dseg segment para public 'data'
    num dw 0
    temp dw 0
	remainder dw 0
	var_a dw 0
	var_b dw 0
	var_x dw 0
	result dw 0
    is_negative dw 0
;0ah is equivalent to 10 in decimal and to linefeed ('\n') in ASCII which moves the cursor 
;to the next row of the screen but maintaining the same column.
;0dh is equivalent to carriage return ('\r') in ASCII which moves the cursor to the beginning of
;the current row.A combination of the two thus moves the cursor to the beginning of the next row.
   ; prompt db 0dh, 0ah, 'enter number in range of -32734 to 32767 : $'
	enterA db 0dh, 0ah, 'enter a in (-32,768; 32767] : $'
	enterB db 0dh, 0ah, 'enter b in (-32,768; 32767]: $'
	enterX db 0dh, 0ah, 'enter x in [-182; 182]: $'
	
    printresult db 0dh, 0ah, 'result: $'
	printA db 0dh, 0ah, 'A is $'
	printB db 0dh, 0ah, 'B is $'
	printX db 0dh, 0ah, 'X is $'
	
	overflow_error db 'Overflow $'
    input_error_message db 0dh, 0ah, 'the number you entered is too large$'
    wrong_character_message db 0dh, 0ah, 'wrong character$'
    empty_input_message db 0dh, 0ah, 'empty input$'
    equals_0 db 0dh, 0ah, 'a + 2b = $'
	less_than_0 db 0dh, 0ah, 'ax^2 - bx = $'
	greater_than_0 db 0dh, 0ah, 'ax^2 + b/x = $'
	div_remainder db ' remainder: $'
	
	
	
dseg ends

cseg segment para public 'code'
    main proc
    assume cs: cseg, ds: dseg, ss: sseg ;WHICH SEGMENT IS ATTACHED TO WHICH REGISTER
    ; return address
    push ds
    xor ax, ax
    push ax
    ; initializing DS
    mov ax, dseg
    mov ds, ax
	
	
	lea dx, enterA
	mov ah, 09h
    int 21h
	
    call read_digit
	mov ax,num
	mov var_a,ax
	call print_var_a
	
	lea dx, enterB
	mov ah, 09h
    int 21h
	
	call read_digit
	mov ax,num
	mov var_b,ax
	call print_var_b
	
	lea dx, enterX
	mov ah, 09h
    int 21h
	
	call read_digit
	mov ax,num
	mov var_x,ax
	call print_var_x
	
	cmp var_x, 0
	jg func_greater_0
	je func_equals_0
	jmp func_less_0
	
	func_greater_0:
		lea dx,greater_than_0
		mov ah,09h
		int 21h
		call function_greater_than_0
		mov ah, 4ch  ;terminate program with return code
		int 21h
	func_less_0:
		lea dx, less_than_0
		mov ah, 09h  ;Writes a string to the display.
		int 21h
		call function_less_than_0
		mov ah, 4ch  ;terminate program with return code
		int 21h
	func_equals_0:
		lea dx, equals_0
		mov ah, 09h
		int 21h
		call function_equals_0
		mov ah, 4ch  ;terminate program with return code
		int 21h
        
main endp ;END OF PROCEDURE


function_greater_than_0 proc
	xor ax,ax
	xor cx,cx
	mov ax, var_x
	mov cx, var_x
	imul cx  ;ax = x^2
	jc func3_overflow
	jo func3_overflow
	mov result, ax   ; result = x^2
	xor ax,ax
	mov ax,var_a
	imul result     ; ax = x^2 * a
	jc func3_overflow
	jo func3_overflow
	mov result, ax   ; result = x^2 * a
	mov ax, var_x
	imul result  ;  ax = a * x^3
	mov result, ax
	mov ax, var_b
	add result, ax ; result = a * x^3 + b
	
	xor ax,ax
	xor bx,bx
	mov ax, result
	mov bx, var_x
	
	idiv bx      ; ax = b/x   dx - remainder
	
	jc func3_overflow
	jo func3_overflow

	mov result, ax
	mov remainder, dx
	call print_result
	
	cmp remainder, 0
	jne func3_remainder
	ret

	func3_remainder:
			
			lea dx, div_remainder
			mov ah, 09h  ;Writes a string to the display.
			int 21h
			call print_num_remainder
			ret
			
	func3_overflow: 
		lea dx, overflow_error
		mov ah, 09h  ;Writes a string to the display.
		int 21h
		ret
function_greater_than_0 endp

function_equals_0 proc
	xor ax,ax
	xor cx,cx
	mov ax, var_b
	mov cx, 2
	imul cx   ; ax = 2b
	jc func2_overflow
	jo func2_overflow
	add ax, var_a
	;;;func2_overflow
	jo func2_overflow
	mov result, ax
	call print_result
	ret
	func2_overflow: 
		lea dx, overflow_error
		mov ah, 09h  ;Writes a string to the display.
		int 21h
		ret
function_equals_0 endp

function_less_than_0 proc
	xor ax,ax
	xor cx,cx
	mov ax,var_x
	mov cx, var_x
	imul cx   ; ax = x^2
	jo func_overflow
	xor cx,cx
	mov cx,var_a
	imul cx ; ax = x^2 * a
	jo func_overflow
	mov result, ax ; result = a*x^2
	xor ax,ax
	xor cx,cx
	mov ax, var_x
	mov cx, var_b
	imul cx ; ax = b*x
	jo func_overflow
	sub result, ax
	call print_result
	ret
	
	func_overflow: 
		lea dx, overflow_error
		mov ah, 09h  ;Writes a string to the display.
		int 21h
		ret
		
function_less_than_0 endp

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
   
   
  ;  cmp ax, 32767
  ;  ja input_error
  
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
    lea dx, input_error_message
    mov ah, 09h
    int 21h
    mov ah, 4ch
    int 21h
    
    wrong_character:
    lea dx, wrong_character_message
    mov ah, 09h
    int 21h
    mov ah, 4ch
    int 21h
    
    empty_input:
    lea dx, empty_input_message
    mov ah, 09h
    int 21h
    mov ah, 4ch
    int 21h


read_digit endp

print_num_remainder proc

	mov bx, remainder
    or bx, bx
    jns mre1
    mov al, '-'
    int 29h
    neg bx

    mre1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
    mre2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz mre2
 
    mre3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop mre3
    ret
print_num_remainder endp

print_result proc

    mov bx, result
    or bx, bx
    jns mr1
    mov al, '-'
    int 29h
    neg bx

    mr1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
    mr2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz mr2
 
    mr3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop mr3
    ret

print_result endp

print_var_a proc

    lea dx, printA
    mov ah, 09h  ;Writes a string to the display.
    int 21h
	
    mov bx, var_a
    or bx, bx
    jns ma1
    mov al, '-'
    int 29h
    neg bx

    ma1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
    ma2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz ma2
 
    ma3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop ma3
    ret

print_var_a endp


print_var_b proc

    lea dx, printB
    mov ah, 09h  ;Writes a string to the display.
    int 21h
	
    mov bx, var_b
    or bx, bx
    jns mb1
    mov al, '-'
    int 29h
    neg bx

    mb1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
    mb2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz mb2
 
    mb3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop mb3
    ret

print_var_b endp


print_var_x proc

    lea dx, printX
    mov ah, 09h  ;Writes a string to the display.
    int 21h
	
    mov bx, var_x
    or bx, bx
    jns mx1
    mov al, '-'
    int 29h
    neg bx

    mx1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
    mx2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz mx2
 
    mx3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop mx3
    ret

print_var_x endp

cseg ends ;END OF CODE SEGMENT
end main  ;END OF PROGRAM