package flyables;

import weather.Coordinates;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(final String name, final Coordinates coordinates)
    {
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId()
    {
        return ++(Aircraft.idCounter);
    }
}