const deprecated = (deprecationReason: string) => {
  return (
    target: any,
    memberName: string,
    propertyDescriptor: PropertyDescriptor
  ) => {
    return {
      get() {
        const wrapperFn = (...args: any[]) => {
          console.warn(
            `Method ${memberName} is deprecated with reason: ${deprecationReason}`
          );
          propertyDescriptor.value.apply(this, args);
        };

        Object.defineProperty(this, memberName, {
          value: wrapperFn,
          configurable: true,
          writable: true,
        });
        return wrapperFn;
      },
    };
  };
};

class TestClass {
  static staticMember = true;

  instanceMember: string = "hello";

  @deprecated("Use another static method")
  static deprecatedMethodStatic() {
    console.log(
      "inside deprecated static method - staticMember =",
      this.staticMember
    );
  }

  @deprecated("Use another instance method")
  deprecatedMethod() {
    console.log(
      "inside deprecated instance method - instanceMember =",
      this.instanceMember
    );
  }
}

TestClass.deprecatedMethodStatic();

const instance = new TestClass();
instance.deprecatedMethod();
