package de.l3s.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class BatchRename {

	public static void main(final String[] args) {
		// use possible command line argument as master URL
		final String master;
		if (args.length > 0) {
			master = args[0];
		} else {
			master = "hdfs://master.ib:8020";
		}

		try {
			final Configuration conf = new Configuration();
			conf.set("fs.defaultFS", master);
			final FileSystem dfs = FileSystem.get(conf);

			final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
			try {
				// measure the runtime
				final long start = System.currentTimeMillis();
				// count renamed files
				int counter = 0;
				String line;
				while ((line = reader.readLine()) != null) {
					// split
					final String[] parts = line.split("\\s+");
					if (parts.length > 2) {
						System.err.println("input line contains more than one column: " + line);
						throw new IllegalArgumentException("input line contains more than one column: " + line);
					}
					final Path from = new Path(parts[0]);
					final Path to = new Path(parts[1]);

					dfs.rename(from, to);
					
					counter++;
				}
				final long end = System.currentTimeMillis();
				System.err.println("renamed " + counter + " files in " + ((end - start)/1000) + " seconds");
			} catch (final IOException e) {
				System.err.println(e);
				e.printStackTrace();
			} finally {
				reader.close();
				dfs.close();
			}

		} catch (final Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}
}