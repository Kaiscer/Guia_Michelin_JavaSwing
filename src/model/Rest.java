package model;



public class Rest {
	
	
	
	private int id;
	private String nombre;	
	private String region;
	private String ciudad;
	private int distincion;
	private String direccion;
	private double precio_Min;
	private double precio_Max;
	private String cocina;
	private String telefono;
	private String web;
	
	public Rest(int id, String nombre, String region, String ciudad, int distincion, String direccion, double precio_Min,
			double precio_Max, String cocina, String telefono, String web) {
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.ciudad = ciudad;
		this.distincion = distincion;
		this.direccion = direccion;
		this.precio_Min = precio_Min;
		this.precio_Max = precio_Max;
		this.cocina = cocina;
		this.telefono = telefono;
		this.web = web;
	}
	
	

	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}

	public String getRegion() {
		return region;
	}

	public String getCiudad() {
		return ciudad;
	}

	public int getDistincion() {
		return distincion;
	}

	public String getDireccion() {
		return direccion;
	}

	public double getPrecio_Min() {
		return precio_Min;
	}

	public double getPrecio_Max() {
		return precio_Max;
	}

	public String getCocina() {
		return cocina;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getWeb() {
		return web;
	}
	
	
	
	

}
