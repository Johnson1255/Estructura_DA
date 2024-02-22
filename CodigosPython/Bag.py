class Bag:

    def __init__(self):
        self.items = [ None, 1, None, 5, 6, None ]

    def removeItem(self):
        for i in range(len(self.items)):
            if self.items[i] is not None:
                x = self.items[i]
                self.items[i] = None
                return x
        raise Exception("La bolsa esta vacia")
    
if __name__ == "__main__":
    b = Bag()
    print(b.removeItem())
    print(b.items)

    print(b.removeItem())
    print(b.items)

    print(b.removeItem())
    print(b.items)

    print(b.removeItem())
    print(b.items)