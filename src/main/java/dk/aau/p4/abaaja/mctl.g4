grammar mctl;

mctl: (declaration | statement | COMMENT | SEMI)* EOF;

block
    : LCURL (declaration | statement | COMMENT | SEMI)* RCURL
    ;

declaration
    : variableDeclaration SEMI
    | function
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
    : ID DOT id                     #idStruct
    | ID (LSQR expression RSQR)+    #idArray
    | ID                            #idVar
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

function
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
    : ID LPAR (actualParameters)? RPAR                                                  #funcInv
    | (ID | STRING) DOT (Add|IndexesOf|SubString|SubList) LPAR actualParameters RPAR    #prodInv
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
    : invoke                                                        #invExp
    | LPAR expression RPAR                                          #parenExp
    | (NOT|MINUS|PLUS) expression                                   #unaryExp
    | LPAR variableType RPAR expression                             #typecast
    | boolean                                                       #boolExp
    | NUMBER                                                        #numberExp
    | id                                                            #idExp
    | STRING                                                        #stringExp
    | expression (MULTIPLY|DIVIDE|MODULO) expression                #mulExp
    | expression (PLUS|MINUS) expression                            #addExp
    | expression (LESS|LESSEQUAL|GREATER|GREATEREQUAL) expression   #compExp
    | expression (EQUAL|NOTEQUAL) expression                        #equalExp
    | expression And expression                                     #andExp
    | expression Or expression                                      #orExp
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