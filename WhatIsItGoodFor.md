# Introduction #

Whenever you have a treelike connected JavaFX control hierarchy, you may want to analyze it. What is better than having a look on it in a simple way?
And this is where tree chart comes in.


# Details #

Just assuming there is a object hierarchy like this:
```
- A
-- B1
-- B2
--- C1
---- D1
----- E1
--- C2
---- D1
-- B3
```

The tree chart comes with an object analyzer, so it is not necessary to set the path manually.

Run `/de.chimos.ui.treechart.test/src/de/chimos/ui/treechart/test/Main02.java` to see it in action.

Have a closer look to `createData()` within `Main02.java` for understanding how the instances were connected.