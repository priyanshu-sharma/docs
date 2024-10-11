def exercise_1(x: Int, y: Int): Int = {
	return (x * x) + (2 * x * y) + (y * y * y) - 1;
}

def exercise_2(x: Int): Int = {
	if (x == 0) {
		return 0;
	}
	else {
		return x + exercise_2(x - 1);
	}
}

def exercise_3(x: (Int, Int, Int)): (Int, Int, Int) = {
	return (x._2, x._3, x._1);
}

def exercise_4(x: Int): String = {
	val m: Int = x % 10;
	m match {
		case 1 => return "st";
		case 2 => return "nd";
		case 3 => return "rd";
		case _ => return "th";
	}
}

abstract class Color
case class Red() extends Color
case class Green() extends Color
case class Blue() extends Color


def exercise_5(c: Color): Boolean = {
	c match {
		case Red() => true;
		case Green() => false;
		case Blue() => false;
	}
}

abstract class Shape
case class Rectangle(x: Int, y: Int, w: Int, h: Int) extends Shape
case class Circle(x: Int, y: Int, r: Int) extends Shape

def exercise_6(s: Shape): Rectangle = {
	s match {
		case Rectangle(x, y, w, h) => Rectangle(x, y, w, h)
		case Circle(x, y, r) => Rectangle(x-r, y-r, 2*r, 2*r)
	}
}

def add2(x: Int): Int = {
	return x + 2;
}

def multiply3(x: Int): Int = {
	return x * 3;
}

def exercise_8(x: Int, f: (y: Int) => Int, g: (z: Int) => Int): Int = {
	return g(f(x));
}

val increment = {(x: Int) => x + 2};
val multiple = {(x: Int) => x * 2};

def exercise_9(x: Int, f: (y: Int) => Int, g: (z: Int) => Int): Int = {
	return g(f(x));
}

def add10(x: Int): Int = {
	return x + 10;
}

def multiply30(x: Int): Int = {
	return x * 30;
}

def exercise_10(x: Int, f: (y: Int) => Int, g: (z: Int) => Int): Int = {
	return g(f(x));
}


def exercise_11(f: (x: Int) => Int, l: List[Int]): List[Int] = {
	l match {
		case Nil => Nil
		case head :: tail => f(head) :: exercise_11(f, tail)
	}
}


val isOdd = {(x: Int) => x % 2 == 1}

def exercise_12(predicate: Int => Boolean, l: List[Int]): List[Int] = {
	l match {
		case Nil => Nil;
		case head :: tail =>
			if (predicate(head)) head :: exercise_12(predicate, tail)
			else exercise_12(predicate, tail)
	}
}


def exercise_13(l: List[Int]): List[Int] = {
	l match {
		case Nil => Nil;
		case head :: tail => exercise_13(tail) :+ head
	}
}


def exercise_14(l: List[(Int, String)], k : Int): String = {
	l match {
		case Nil => throw new NoSuchElementException("Key not found");
		case (key, value) :: tail =>
			if (key == k) {
				return value;
			}
			else {
				return exercise_14(tail, k);
			}
	}
}


def exercise_15(l: List[(Int, String)], k: Int, v: String): List[(Int, String)] = {
	l match {
		case Nil => Nil;
		case (key, value) :: tail =>
			if (key == k) {
				return (key, v) :: exercise_15(tail, k, v);
			}
			else {
				return (key, value) :: exercise_15(tail, k, v);
			}
	}
}


def exercise_16(l: List[(Int, String)]): List[Int] = {
	l match {
		case Nil => Nil;
		case (key, value) :: tail =>
			return key :: exercise_16(tail);
	}
}


def exercise_17(): scala.collection.immutable.ListMap[Int, String] = {
	val presidentNumberNameMap = scala.collection.immutable.ListMap(41 -> "George H. W. Bush", 42 -> "Bill Clinton", 43 -> "George W. Bush", 44 -> "Barack Obama", 45 -> "Donald J. Trump");
	return presidentNumberNameMap;
}


def exercise_18(k: List[(Int, String)], output: scala.collection.immutable.ListMap[Int, String]): scala.collection.immutable.ListMap[Int, String] = {
	k match {
		case Nil => return output;
		case (key, value) :: tail => return exercise_18(tail, output + (key -> value));
	}
}


def exercise_19(k: List[(Int, String)], output: scala.collection.immutable.ListMap[Int, String]): scala.collection.immutable.ListMap[Int, String] = {
	k match {
		case Nil => output;
		case (key, value) :: tail => return exercise_19(tail, output + (key -> value));
	}
}


def add_data(election_data: String, electionMap: scala.collection.immutable.ListMap[String, Int]): scala.collection.immutable.ListMap[String, Int] = {
	election_data match {
		case Nil => return electionMap + (election_data -> 1);
		case (key, value) :: tail => 
			if (key == election_data) {
				value = value + 1;
			}
	}
}

def exercise_20(election: List[String]): scala.collection.immutable.ListMap[String, Int] = {
	val electionMap: scala.collection.immutable.ListMap[String, Int] = scala.collection.immutable.ListMap[String, Int]();
	election.foreach(election_data => add_data(election_data, electionMap));
	return electionMap;
}

@main def entrypoint(): Unit = {
	println("exercise_1 - " + exercise_1(2, 3));
	println("exercise_2 - " + exercise_2(10));
	println("exercise_3 - " + exercise_3((1, 2, 3)));
	println("exercise_4 - " + exercise_4(1));
	println("exercise_4 - " + exercise_4(2));
	println("exercise_4 - " + exercise_4(3));
	println("exercise_4 - " + exercise_4(4));
	println("exercise_4 - " + exercise_4(40));
	var r = new Red();
	println("exercise_5 - " + exercise_5(r));
	var g = new Green();
	println("exercise_5 - " + exercise_5(g));
	var b = new Blue();
	println("exercise_5 - " + exercise_5(b));
	var rectangle = new Rectangle(4, 6, 2, 4);
	println("exercise_6 - " + exercise_6(rectangle));
	var circle = new Circle(4, 6, 2);
	println("exercise_6 - " + exercise_6(circle));
	println("exercise_8 - " + exercise_8(4, add2, multiply3));
	println("exercise_9 - " + exercise_9(1, increment, multiple));
	println("exercise_10 - " + exercise_10(1, add10, multiply30));
	println("exercise_10 - " + exercise_10(1, multiply30, add10));
	val l: List[Int] = List(1, 2, 3, 4)
	println("exercise_11 - " + exercise_11(add10, l));
	println("exercise_12 - " + exercise_12(isOdd, l));
	println("exercise_13 - " + exercise_13(l));
	val lmap: List[(Int, String)] = List((1, "a"), (2, "b"), (3, "c"), (4, "d"))
	println("exercise_14 - " + exercise_14(lmap, 4));
	println("exercise_15 - " + exercise_15(lmap, 3, "g"));
	println("exercise_16 - " + exercise_16(lmap));
	println("exercise_17 - " + exercise_17());
	val em: scala.collection.immutable.ListMap[Int, String] = scala.collection.immutable.ListMap[Int, String]()
	val k: List[(Int, String)] = List((1, "one"), (2, "two"), (3, "three"), (4, "four"));
	println("exercise_18 - " + exercise_18(k, em));
	println("exercise_19 - " + exercise_19(k, em));
	val election: List[String] = List("Hillary","Donald","Hillary");
	println("exercise_20 - " + exercise_20(election));
}