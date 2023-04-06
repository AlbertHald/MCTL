grammar mctl;

mctl: (line)* EOF;

block
    : LCURL (line)* RCURL
    ;

line
    : declaration
    | statement
    | COMMENT
    | SEMI;

declaration
    : variableDeclaration SEMI
    | functionDeclaration
    | structDeclaration
    ;

variableDeclaration
    : Variable ID COLON variableType
    ;

structDeclaration
    : Struct ID structBlock
    ;

structBlock
    : LCURL variableDeclaration (COMMA variableDeclaration)* (COMMA)? RCURL
    ;

id
    : ID                                    #idVar
    | id DOT id                             #idStruct
    | id (LSQR expression RSQR)+            #idArray
    ;

statement
    : if
    | repeat
    | assignment SEMI
    | invoke SEMI
    | Stop SEMI
    | return SEMI
    ;

return
    : Return expression
    ;

functionDeclaration
    : To ID LPAR (formalParameters)? RPAR COLON returnType block
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
    : id ASSIGN expression #exprAss
    | id PLUS PLUS         #incrAss
    ;

invoke
    : (id DOT)? ID LPAR (actualParameters)? RPAR     #funcInv
    ;

formalParameters
    : (formalParameter COMMA)* formalParameter (COMMA)?
    ;

formalParameter
    : ID COLON variableType
    ;

actualParameters
    : (expression COMMA)* expression (COMMA)?
    ;

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
    : variableType
    | Nothing
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
Add: 'add';
IndexesOf: 'indexesOf';
SubString: 'subString';
SubList: 'subList';
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

COMMENT: '#{' .*? '}' -> skip;
WS: [ \n\t\r]+ -> skip;