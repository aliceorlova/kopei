sseg segment para stack 'stack'; sseg - segment name, segment - reserved word
;para-alignment type(paragraph),stack-for any future segment linking,'stack' - name for linking
    db 64 dup ( 'STACK' )
sseg ends

dseg segment para public 'data'
    num dw 0
    temp dw 0
    is_negative dw 0
;0ah is equivalent to 10 in decimal and to linefeed ('\n') in ASCII which moves the cursor 
;to the next row of the screen but maintaining the same column.
;0dh is quivalent to carriage return ('\r') in ASCII which moves the cursor to the beginning of
;the current row.A combination of the two thus moves the cursor to the beginning of the next row.
    prompt db 0dh, 0ah, 'enter number in range of -32734 to 32767 : $'
    result db 0dh, 0ah, 'result: $'
    input_error_message db 0dh, 0ah, 'the number you entered is too large$'
    subtract_error db 0dh, 0ah, 'the result of substraction is more than -32767$'
    wrong_character_message db 0dh, 0ah, 'wrong character$'
    empty_input_message db 0dh, 0ah, 'empty input$'
    
dseg ends

cseg segment para public 'code'

print macro string
	push ax
	lea dx,string
	mov ah,09h
	int 21h
	pop ax
endm


print_digit macro num
	local m1, m2, m3
    print result

    mov bx, num
    or bx, bx
    jns m1
    mov al, '-'
    int 29h
    neg bx

     m1:
    mov ax, bx
    xor cx, cx  ; cx used as a counter of number's decimal places
    mov bx, 10
 
     m2:         ; divide by 10 and add to the remainder zero's ascii code(48)
    xor dx, dx  ; repeat until the integer part will not equal 0
    div bx
    add dl, '0'
    push dx
    inc cx
    test ax, ax
    jnz m2
 
     m3:         ; begin printing starting from the most significant digit
    pop ax
    int 29h
    loop m3

endm

    main proc
    assume cs: cseg, ds: dseg, ss: sseg ;WHICH SEGMENT IS ATTACHED TO WHICH REGISTER
    ; return address
    push ds
    xor ax, ax
    push ax
    ; initializing DS
    mov ax, dseg
    mov ds, ax

    call read_digit
    call substract_34
    
    mov ah, 4ch  ;terminate program with return code
    int 21h
        
main endp ;END OF PROCEDURE


substract_34 proc

    sub num, 34
    jo add_error
	print_digit num
    ret
    
    add_error:
    lea dx, subtract_error
    mov ah, 09h
    int 21h
    mov ah, 4ch
    int 21h
    
substract_34 endp


read_digit proc

    lea dx, prompt
    mov ah, 09h
    int 21h

    mov bx, 10
    mov cx, 5
    
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



cseg ends ;END OF CODE SEGMENT
end main  ;END OF PROGRAM