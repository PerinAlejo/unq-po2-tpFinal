package unq.po2.tpFinal;

import java.util.List;

import unq.po2.tpFinal.domain.Service;

public interface Services{
	public void add(Service service);
	public List<Service> getAll();
}