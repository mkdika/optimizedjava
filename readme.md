## Optimized & GC Friendly Java Code Guide

### Loops
For Java Collection API (non-arrays), to traverse all elements, DONT use `for-each-loop`, as shown below:

```java
Benefit benefit = ...
for (Member m : goldMember) {
	m.setBenefit(benefit);
}
```

Because during the code generation `for-each-loop` is replace with standard iterator loop. Instead of that we can use `lambda for-each` or `index-loop`. Below is the solution using `lambda for-each`:

```java
Benefit benefit = ...
goldMember.forEach(m -> m.setBenefit(benefit));
```

or using `index-loop`:

```java
Benefit benefit = ...
for(int i=0;i < goldMember.size();i++) {
	goldMember.get(i).setBenefit(benefit);
}
```




### Reference
- [Writing GC Friendly Code](https://github.com/AlmasB/FXGL/wiki/Writing-GC-friendly-Code)
