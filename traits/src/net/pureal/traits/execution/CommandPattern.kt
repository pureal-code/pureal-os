package net.pureal.traits.execution

public enum class CommandPattern {
    Unary    // The operator is before the operand
    Binary   // v1 op v2
    Function // fn(parameters)
    Bracing  // e.g. { ... }
}