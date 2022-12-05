const allowlistOnly = (allowlist: string[]) => {
  console.log("from decorator factory method");

  return (target: any, memberName: string) => {
    console.log("from decorator");
    let currentValue: any = target[memberName];

    Object.defineProperty(target, memberName, {
      set: (newValue: any) => {
        if (!allowlist.includes(newValue)) {
          //check if exist in allow list
          return;
        }
        currentValue = newValue;
      },
      get: () => currentValue,
    });
  };
};

class Person {
  @allowlistOnly(["Claire", "Oliver"])
  name: string = "Claire";
}

const person = new Person();
console.log(person.name);
person.name = "Peter";
console.log(person.name);
person.name = "Oliver";
console.log(person.name);
