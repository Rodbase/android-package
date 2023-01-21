package com.rodbase.rodbase;

public interface RodbaseAuthListener{
    void OnCompleted(RodbaseAuthUser user);
    void OnError(String message);
}
