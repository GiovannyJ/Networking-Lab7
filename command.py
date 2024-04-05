class Command:
    def __init__(self, commandName):
        self.commandName = commandName
        self.instructions = ""
        self.response = ""
        self.error = False
        self.isExecuted = False
    
    def isError(self):
        return self.error
    
    def runCommand(self):
        if self.commandName == "run app":
            print("running app")
        else:
            print("idk that command")

thing = [1, 2, 3, 5]
thing.append(8)
print(thing)

c = Command("run app")
c.runCommand()


