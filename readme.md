## Write the GC Friendly Java Code
Tips & Guide for Reducing Java Garbage Collection Overhead

### Main Idea
coming soon...


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

Because during the code generation `for-each-loop` is replace with standard `iterator-loop`, and `iterator()` is likely to produce a new iterator on each call. Instead of that we can use `lambda for-each` or `index-loop`. Below is the solution using `lambda for-each`:

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

For example code related to above tips, please browse to this [Link](https://github.com/mkdika/optimizedjava/blob/master/optimizedjava/src/com/mkdika/optimizedjava/loops/TestLoop1.java)


### Predict Collection Capacities
The idea is to avoid or optimized collection size re-allocation process by providing its expected size upon construction.

```java
List<Member> members = ...
List<String> midMembers = new ArrayList<>();
for (int i=0; i < members.size(); i++) {
	if (members.get(i).getTransaction() > 1000) {
		midMembers.add(members.get(i));
	}
}
```

Instead of above code, we can initialize the size of `ArrayList` for object `midMembers` as below:

```java
List<Member> members = ...
List<String> midMembers = new ArrayList<>(members.size());
for (int i=0; i < members.size(); i++) {
	if (members.get(i).getTransaction() > 1000) {
		midMembers.add(members.get(i));
	}
}
```
For example code related to above tips, please browse to this [Link](https://github.com/mkdika/optimizedjava/blob/master/optimizedjava/src/com/mkdika/optimizedjava/loops/TestLoop1.java)


### Use Final for Immutable Instance Variable  
Unless for constants purpose, for any immutable instance variable, it is adviced to use `final` modifier. The value of variable can be 
assign upon construction.

```java
public class ObjectPair {
	private final Object first;
	private final Object second;
 
	public ObjectPair(Object first, Object second) {
		this.first = first;
		this.second = second;
	}
 
	public Object getFirst() {
		return first;
	}
 
	public Object getSecond() {
		return second;
	}
}
```






### Reference
- [Writing GC Friendly Code](https://github.com/AlmasB/FXGL/wiki/Writing-GC-friendly-Code)
- [VOID OBJ11-J. Write garbage-collection-friendly code](https://www.securecoding.cert.org/confluence/display/java/VOID+OBJ11-J.+Write+garbage-collection-friendly+code)
- [5 Tips for Reducing Your Java Garbage Collection Overhead](http://blog.takipi.com/5-tips-for-reducing-your-java-garbage-collection-overhead/)
