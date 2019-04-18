package edu.metroState.ics372GroupProject3.dataCollector;

import java.io.File;
import java.io.IOException;

public interface IReader {
	public Readings read(File file)throws IOException;
}
