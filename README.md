# RPNCalculator
A SpringBoot demo for RPN calculator
-The calculator has a stack that can contain real numbers.
-The calculator waits for user input and expects to receive strings containing whitespace separated lists of numbers and operators.
-Numbers are pushed on to the stack. Operators operate on numbers that are on the stack.
-Available operators are +, -, *, /, sqrt, undo, clear
-Operators pop their parameters off the stack, and push their results back onto
the stack.
-The ¡®clear¡¯ operator removes all items from the stack.
-The ¡®undo¡¯ operator undoes the previous operation. ¡°undo undo¡± will undo the
previo us two operations.
-sqrt performs a square root on the top item from the stack
-The ¡®+¡¯, ¡®-¡¯, ¡®*¡¯, ¡®/¡¯ operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack.