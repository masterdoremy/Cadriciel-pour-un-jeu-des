public class De implements Comparable<De> {
    private int nbFaces;
    private int numeroDe;

    public De(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    public void setFaceObtenue(int faceObtenue) {
        this.numeroDe = faceObtenue;
    }

    public void roll()
    {
        this.numeroDe = (int)(Math.random() * nbFaces) + 1;
    }

    @Override
    public int compareTo(De o) {
        return Integer.compare(this.getNumeroDe(),o.getNumeroDe());
    }

    @Override
    public String toString()
    {
        String result = Integer.toString(this.nbFaces);
        return result;
    }

    public int getNumeroDe() {
        return numeroDe;
    }
}