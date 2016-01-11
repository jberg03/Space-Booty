package com.johnberg.spacebooty.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Space-Booty
 * Created by john on 1/10/16.
 * <p/>
 * Copyright His Light Game Studio 2016
 */
public class Packer {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("Input", "Output", "Game.pack");
    }
}
