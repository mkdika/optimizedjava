## GC Friendly Java Code
Tips & Guide for Reducing Java Garbage Collection Overhead

### Main Idea


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

For example code for this tips, please browse to this [Link](https://github.com/mkdika/optimizedjava/blob/master/optimizedjava/src/com/mkdika/optimizedjava/loops/TestLoop1.java)



### Reference
- [Writing GC Friendly Code](https://github.com/AlmasB/FXGL/wiki/Writing-GC-friendly-Code)
- [VOID OBJ11-J. Write garbage-collection-friendly code](https://www.securecoding.cert.org/confluence/display/java/VOID+OBJ11-J.+Write+garbage-collection-friendly+code)
- [5 Tips for Reducing Your Java Garbage Collection Overhead](http://blog.takipi.com/5-tips-for-reducing-your-java-garbage-collection-overhead/)
