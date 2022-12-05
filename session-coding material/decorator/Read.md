# Decorator

- Decorators are a design pattern that is used to separate modification or decoration of a class without modifying the original source code

- decorator (also known as a decorator function) can additionally refer to the design pattern that wraps a function with another function to extend its functionality.

## What is a Decorator?
The decorator is a function that we can hook into our code, to extend with some behavior and helps us to write code abstractions that help extend code clear.

The decorator function depends on which you will decorate, that's mean it doesn't get the same parameters when it is working over a class, methods or properties.

## Where can I Use Decorators?
The decorators are used in some class code areas.

- class definition
- properties
- methods
- accessors
- parameters.

## example

#### before
```ts
class BaseEntity {
  readonly id: number;
  readonly created: string;
  constructor() {
    this.id = Math.random();
    this.created = new Date().toLocaleDateString();
  }
}

class Course extends BaseEntity {
    constructor(public name: string) {
        super();
    }
}

let englishCourse = new Course("English");
console.log("id: " + englishCourse.id);
console.log("created: " + englishCourse.created);
```

#### after
The decorator is ready to be used in each entity 
without modifying his constructor or extend,
only needs to add @Entity before class definition.

```ts
function BaseEntity(ctr: Function) {
  ctr.prototype.id = Math.random();
  ctr.prototype.created = new Date().toLocaleString("es-ES");
}

@BaseEntity
class User {
    constructor(public name: string) {}
}

@BaseEntity
class City {
    constructor(public zicode: number) {}
}

let user = new User("dany");
let ny = new City("RD");
//City and User classes has the id and created property ;)
console.log(ny.id);
console.log(user.id);
```
## Decorator Factory
The decorator factory is a function that returns 
the decorator function itself, it gives the 
flexibility to pass parameters to the decorators.

```ts
function LuckyNumber(limit: number) { 
  return function (constructor: Function) { 
    constructor.prototype.lucky = Math.floor(
      Math.random() * Math.floor(limit)
  }
}

@BaseEntity
@LuckyNumber(3)
class User {
    constructor(public name: string) {}
}

@BaseEntity
@LuckyNumber(3)
class City {
    constructor(public zicode: number) {}
}

let user = new User("dany");
let ny = new City(08930);
//the City and User has the lucky number property
console.log(ny.lucky);
console.log(user.lucky);

```
