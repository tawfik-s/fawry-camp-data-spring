const enumerable = (value: boolean) => {
  return (
    target: any,
    memberName: string,
    propertyDescriptor: PropertyDescriptor
  ) => {
    propertyDescriptor.enumerable = value;
  };
};

class Person2 {
  firstName: string = "Jon";
  lastName: string = "Doe";

  @enumerable(true)
  get fullName() {
    return `${this.firstName} ${this.lastName}`;
  }
}

console.log(new Person2().fullName);
