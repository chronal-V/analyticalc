import expressions.Expression

class Function(variable: String, expr: Expression) {

    /*
       ------------
      | Nullstellen |
       ------------
     */
    fun getRoots() : List<Double> {
        return emptyList();
    }

    /*
       ---------------
      | Extremstellen |
       ---------------
     */
    fun getExtremes() : List<Double> {
        //TODO: hinreichendes Kriterium!
        return this.getDerivative().getRoots()
    }

    /*
       --------------
      | Wendestellen |
       --------------
     */
    fun getInflection() : List<Double> {
        return emptyList()
    }

    /*
       -----------
      | Ableitung |
       -----------
     */
    fun getDerivative() : Function {
        return this
    }

    /*
       ---------------
      | Stammfunktion |
       ---------------
     */
    fun getIntegral() : Function {
        return this
    }

    /*
       ----------------------
      | Integral von a bis b |
       ----------------------
     */
    fun getIntegral(a: Double, b: Double) : Double {
        return b - a;
    }


}