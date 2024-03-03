package Model;

public class SquareFactory {
	public SquareInterface getSquare(String type, int i, int columnIndex)
	{
		if (type == null || type.isEmpty())
            return null;
        switch (type) {
        case "Square":
            return new Square(i,columnIndex);
        case "SurpriseSquare":
            return new SurpriseSquare(i,columnIndex);
        case "QuestionSquare":
            return new QuesSquare(i,columnIndex, -1);
        default:
            throw new IllegalArgumentException("Unknown type "+type);
        }
	}

}
