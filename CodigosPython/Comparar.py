class Fecha:
    def __lt__(self,f):
        if self.ano != f.ano:
            return self.ano - f.ano
        if self.mes != f.mes:
            return self.mes - f.mes
        if self.dia != f.dia:
            return self.dia - f.dia
        return 0
        
        