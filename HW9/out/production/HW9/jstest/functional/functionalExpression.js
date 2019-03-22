function multipleOperator(f, arity) {
    var ret = function () {
        var internalArgs = arguments;
        return function (x, y, z) {
            var mappedValues = [];
            for (var i = 0; i < internalArgs.length; ++i) {
                mappedValues[i] = internalArgs[i](x, y, z);
            }
            return mappedValues.reduce(function (init, cur) {
                return f(init, cur);
            });
        }
    };
    ret.arity = arity;
    return ret;
}

function unOperator(f){
    var ret = function(a){
        return function(x, y, z){
            return f(a(x, y, z));
        }
    };
    ret.arity = 1;
    return ret;
}

function variable(name) {
    return function (x, y, z) {
        if (name === 'x') {
            return x;
        }
        if (name === 'y') {
            return y;
        }
        if (name === 'z') {
            return z;
        }
    }
}

function cnst(a) {
    return function (x, y, z) {
        return a;
    }
}

var pi = cnst(Math.PI);
var e = cnst(Math.E);

var min3 = multipleOperator(function (a, b) {
    return Math.min(a, b);
}, 3);

var max5 = multipleOperator(function (a, b) {
    return Math.max(a, b);
}, 5);

var add = multipleOperator(function (a, b) {
    return a + b;
}, 2);

var subtract = multipleOperator(function (a, b) {
    return a - b;
}, 2);

var multiply = multipleOperator(function(a, b){
    return a * b;
}, 2);

var divide = multipleOperator(function(a, b){
    return a / b;
}, 2);

var negate = unOperator(function(a){
    return -a;
});

var cube = unOperator((function(a){
    return a * a * a;
}));

var cuberoot = unOperator(function(a){
    return Math.pow(a, 1 / 3);
});

var functionNames = {'+': add, '-': subtract, '/': divide, '*': multiply, 'max5': max5, 'min3': min3, 'negate': negate, 'cube': cube, 'cuberoot': cuberoot};

var constNames = {'e': e, 'pi': pi};
var varNames = {'x': variable('x'), 'y': variable('y'), 'z': variable('z')};

function splitter(str) {
    return str.split(/[ \t\r\n]/).filter(function (value) {
        return !('' === value);
    });
}

function parse(str) {
    var tokens = splitter(str);
    var stack = [];
    for (var i = 0; i < tokens.length; ++i) {
        var t = tokens[i];
        if (functionNames[t] !== undefined) {
            var curFunc = functionNames[t];
            var args = [];
            for (var j = 0; j < curFunc.arity; ++j) {
                args.push(stack.pop());
            }

            stack.push(curFunc.apply(null, args.reverse()));
            continue;
        }

        if (constNames[t] !== undefined) {
            stack.push(constNames[t]);
            continue;
        }

        if (varNames[t] !== undefined) {
            stack.push(varNames[t]);
            continue;
        }

        stack.push(cnst(parseFloat(t)));
    }
    return stack.pop();
}