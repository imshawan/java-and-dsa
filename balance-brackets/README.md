# Balanced Brackets Checker

## Problem Statement

Implement a function to determine whether a given string containing **parentheses `()`**, **square brackets `[]`**, and **curly braces `{}`** is **balanced**.

A string is considered **balanced** if:

-   Every opening symbol has a corresponding closing symbol.
    
-   Brackets are closed in the **correct order** (i.e., they must be properly nested).
    

----------

##  Examples

### Balanced Strings

-   `(){}[]`
    
-   `({[]})`
    
-   `([])`
    
-   `{[({})]}`
    

### Not Balanced Strings

-   `({[)}]`
    
-   `((())`
    

----------

##  Approach

You can solve this problem using a **stack**:

1.  Traverse each character in the string.
    
2.  Push all **opening brackets** (`(`, `[`, `{`) onto the stack.
    
3.  For each **closing bracket**, check if it matches the top of the stack:
    
    -   If the stack is empty or the brackets don’t match, the string is unbalanced.
        
4.  After processing, if the stack is not empty, the string is unbalanced.