class Polinomio:

    def __init__(self, coef) -> None:
        self._coef = coef
        self._grado = len(coef)-1

    def suma(self, pol: 'Polinomio') -> 'Polinomio':
        grado = self._grado if self._grado>pol._grado else pol._grado
        res = [ 0 for i in range(0,grado+1)]
        for i in range(self._grado+1):
            res[i] += self._coef[i]
        for i in range(pol._grado+1):
            res[i] += pol._coef[i]
        return Polinomio(res)
    
    def getGrado(self):
        return self._grado
            
    def __eq__(self, pol: 'Polinomio') -> bool:
        if pol is None:
            return False
        if isinstance(pol, Polinomio):
            return self._coef == pol._coef
        return False
    
    def __str__(self):
        s = str(self._coef[0])
        for i in range(1,self._grado+1):
            s += f" + {self._coef[i]}*x**{i}"
        return s

if __name__ == "__main__":
    p1 = Polinomio([1,2,3])
    #p2 = Polinomio([1,2,3])
    p2 = Polinomio([1,2,4])
    p3 = p1.suma(p2)
    #assert(p3._coef == [2,4,6])

    #assert(p1==p2)
    assert(p1!=p2)
    assert(str(p1== "1 + 2*x**1 + 3*x**2"))

    print(p1)
    print(p2)
    print(p3)