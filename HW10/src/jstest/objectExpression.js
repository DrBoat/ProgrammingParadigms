'use strict';

function Expression(operation, id, operands) {
    this.operation = operation;
    this.operands = operands;
    this.id = id;
}

Expression.prototype = {
    evaluate: function () {
        var arr = [];
        for (var i = 0; i < this.operands.length; i++) {
            arr.push(this.operands[i].evaluate.apply(this.operands[i], arguments));
        }
        if (arr.length === 0) {
            return this.operation.apply(null, arguments);
        }
        return this.operation.apply(null, arr);
    },

    toString: function() {
        return this.toStringws().trim();
    },

    toStringws: function() {
        var arr = [];
        for (var i = 0; i < this.operands.length; i++) {
            arr.push(this.operands[i].toStringws.call(this.operands[i]));
        }
        return arr.join("").concat(this.id, " ");
    }
};

function createOperator(operation, id) {
    function Operator() {
        Expression.call(this, operation, id, arguments)
    }
    Operator.prototype = Object.create(Expression.prototype);
    return Operator;
}

var Add = createOperator(function (a, b) {
    return a + b;
}, "+");

var Subtract = createOperator(function (a, b) {
    return a - b;
}, "-");

var Multiply = createOperator(function (a, b) {
    return a * b;
}, "*");

var Divide = createOperator(function (a, b) {
    return a / b;
}, "/");

var Negate = createOperator(function (a) {
    return -a;
}, "negate");

var Square = createOperator(function (a) {
    return a * a;
}, "square");

var Sqrt = createOperator(function (a) {
    return Math.sqrt(Math.abs(a));
}, "sqrt");

var Const = function (value) {
    Expression.call(this, function() {return value}, value.toString(), {});
};

Const.prototype = Object.create(Expression.prototype);

var Variable = function (name) {
    function identifyValue() {
        if (name === "x") {
            return arguments[0];
        }
        else if(name === "y") {
            return arguments[1];
        }
        else {
            return arguments[2];
        }
    }
    Expression.call(this, identifyValue, name, {});
};
Variable.prototype = Object.create(Expression.prototype);