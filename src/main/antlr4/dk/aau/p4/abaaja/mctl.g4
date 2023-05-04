grammar mctl;

mctl: (declaration | statement | comment | SEMI)* EOF;

block
    : LCURL (declaration | statement | comment | SEMI)* RCURL
    ;

comment
    : COMMENT
    ;

// Declaration productions for variables, functions, and structs
declaration
    : variableDeclaration SEMI                                                         #varDecl
    | To ID LPAR (formalParameters)? RPAR COLON returnType block                       #functionDeclaration
    | Struct ID LCURL variableDeclaration (COMMA variableDeclaration)* (COMMA)? RCURL  #structDeclaration
    ;

variableDeclaration
    : Variable ID COLON variableType
    ;

// Identifier productions
id
    : ID                                    #idVar
    | id DOT id                             #idStruct
    | id LSQR expression RSQR               #idArray
    ;

// Statement productions
statement
    : if                                    #ifStatement
    | repeat                                #repeatStatement
    | assignment SEMI                       #assignmentStatement
    | invoke SEMI                           #invokeStatement
    | Stop SEMI                             #stopStatement
    | return SEMI                           #returnStatement
    ;

return
    : Return (expression)?
    ;


if
    : ifLiteral block (Else ifLiteral block)* (Else block)?
    ;

ifLiteral
    : If LPAR expression RPAR
    ;

repeat
    : Repeat LPAR expression RPAR block
    ;

assignment
    : id op=ASSIGN expression           #exprAss
    | id op=(INCREMENT | DECREMENT)     #incrAss
    ;

invoke
    : ID LPAR (actualParameters)? RPAR              #functionInvoke
    | id DOT ID LPAR (actualParameters)? RPAR       #varMethodInvoke
    | STRING DOT ID LPAR (actualParameters)? RPAR   #stringMethodInvoke
    ;

// Parameter productions
formalParameters
    : (formalParameter COMMA)* formalParameter (COMMA)?
    ;

formalParameter
    : ID COLON variableType
    ;

actualParameters
    : (expression COMMA)* expression (COMMA)?
    ;

// Expression productions sorted using the specified operator precedence
expression
    : invoke                                                           #invExpr
    | LPAR expression RPAR                                             #parenExpr
    | op=(NOT|MINUS|PLUS) expression                                   #unaryExpr
    | LPAR variableType RPAR expression                                #typecast
    | boolean                                                          #boolExpr
    | NUMBER                                                           #numberExpr
    | id                                                               #idExpr
    | STRING                                                           #stringExpr
    | expression op=(MULTIPLY|DIVIDE|MODULO) expression                #mulExpr
    | expression op=(PLUS|MINUS) expression                            #addExpr
    | expression op=(LESS|LESSEQUAL|GREATER|GREATEREQUAL) expression   #compExpr
    | expression op=(EQUAL|NOTEQUAL) expression                        #equalExpr
    | expression And expression                                        #andExpr
    | expression Or expression                                         #orExpr
    ;

returnType
    : variableType                  #returnTypeVariable
    | Nothing                       #returnTypeNothing
    ;

variableType
    : baseVariableType (LSQR RSQR)*
    ;

baseVariableType
    : Boolean
    | String
    | Number
    | ID
    ;

boolean
    : (True|False)
    ;

EQUAL: '==';
NOTEQUAL: '!=';
LESS: '<';
LESSEQUAL: '<=';
GREATER: '>';
GREATEREQUAL: '>=';

DOT:'.';
COMMA:',';
MULTIPLY: '*';
DIVIDE:'/';
MODULO:'%';
DECREMENT: '--';
INCREMENT: '++';
MINUS: '-';
PLUS: '+';
ASSIGN: '=';
LPAR: '(';
RPAR: ')';
LSQR: '[';
RSQR: ']';
LCURL: '{';
RCURL: '}';
STRING: ('"' .*? '"'|'\'' .*? '\'');
COLON: ':';
SEMI: ';';
NOT: '!';

Nothing: 'NOTHING';
String: 'STRING';
Number: 'NUMBER';
Boolean: 'BOOLEAN';
Struct: 'struct';
True: 'true';
False: 'false';
And: 'and';
Or: 'or';
Stop: 'stop';
Return: 'return';
To: 'to';
Variable: 'variable';
If: 'if';
Else: 'else';
Repeat: 'repeat';

ID: [a-zA-Z_] [a-zA-Z0-9_]*;
NUMBER: [0-9]+('.'[0-9]*)?;

COMMENT: '#{' .*? '}';
WS: [ \n\t\r]+ -> skip;