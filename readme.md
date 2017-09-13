## Optimized & GC Friendly Java Code Guide

### Requirement
- All the example is base on Java 8 code, so you need to have pre-installed `JDK 8 `.


### Loops
For Java Collection API (non-arrays), to traverse all elements, DONT use `for-each-loop`, as shown below:

```java
Benefit benefit = ...
for (Member m : goldMembers) {
	m.setBenefit(benefit);
}
```

Because during the code generation `for-each-loop` is replace with standard `iterator-loop`. Instead of that we can use `lambda for-each` or `index-loop`. Below is the solution using `lambda for-each`:

```java
Benefit benefit = ...
goldMembers.forEach(m -> m.setBenefit(benefit));
```

or using `index-loop`:

```java
Benefit benefit = ...
for(int i=0;i < goldMembers.size();i++) {
	goldMember.get(i).setBenefit(benefit);
}
```




### Reference
- [Writing GC Friendly Code](https://github.com/AlmasB/FXGL/wiki/Writing-GC-friendly-Code)
