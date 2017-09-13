## Write the GC Friendly Java Code
Tips & Guide for Reducing Java Garbage Collection Overhead.

![Imgur](https://i.imgur.com/GO3A9yQ.png)

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
For any instance variable that have immutable purpose, it is adviced to use `final` modifier. The value of variable can be 
assign upon construction, not to directly assign.

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

### String Concatenation
Because `String` are immutable, which means they do not get modified when literally concatenation using the `+` operator takes place.
Use `StringBuilder` and its `append` method to concatenate the chunks of `String`. Instead use this:

```java
public static String toString(T[] array) {
	String result = "[";
 	for (int i = 0; i < array.length; i++) {
		result += (array[i] == array ? "this" : array[i]);
		if (i < array.length - 1) {
			result += ", ";
		}
	} 
	result += "]"; 
	return result;
}
```
We can use this:

```java
public static String toString(T[] array) {
	StringBuilder sb = new StringBuilder("[");
 	for (int i = 0; i < array.length; i++) {
		sb.append(array[i] == array ? "this" : array[i]);
		if (i < array.length - 1) {
			sb.append(", ");
		}
	} 
	sb.append("]");
	return sb.toString();
}
```

### Local Variable Nulling
Local variable nulling is not necessary. The Just In Time (JIT) can do liveness analysis. below are unnecessary manual object nulling.

```java
void foo() {
	int[] array = new int[1024];
	populate(array);
	print(array); // last use of array in method foo()
	array = null; // this line is unnecessary.
	              // array is no longer considered live by the GC  
}
```




### Reference
- [Writing GC Friendly Code](https://github.com/AlmasB/FXGL/wiki/Writing-GC-friendly-Code)
- [VOID OBJ11-J. Write garbage-collection-friendly code](https://www.securecoding.cert.org/confluence/display/java/VOID+OBJ11-J.+Write+garbage-collection-friendly+code)
- [5 Tips for Reducing Your Java Garbage Collection Overhead](http://blog.takipi.com/5-tips-for-reducing-your-java-garbage-collection-overhead/)
