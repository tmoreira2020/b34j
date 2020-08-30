/**
 * Copyright © 2018 Thiago Moreira (tmoreira2020@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiagomoreira.b3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.thiagomoreira.b3.domain.Candlestick;
import net.lingala.zip4j.ZipFile;

public class HistoricReader {

	protected static Logger log = LoggerFactory.getLogger(HistoricReader.class);
	protected DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	protected Candlestick convert(String line, int bdiCode)
			throws ParseException {
		if (line.startsWith("01")) {
			Date date = dateFormat.parse(line.substring(2, 10));
			String code = line.substring(12, 24).trim();
			int temp = Integer.valueOf(line.substring(10, 12).trim());
			if (bdiCode > 0 && bdiCode != temp) {
				return null;
			}

			Candlestick candlestick = new Candlestick(date, code);
			candlestick.setBdiCode(temp);
			candlestick.setType(Integer.valueOf(line.substring(24, 27).trim()));

			candlestick
					.setPriceOpen(Double.parseDouble(line.substring(56, 69)) / 100.0);
			candlestick
					.setPriceMax(Double.parseDouble(line.substring(69, 82)) / 100.0);
			candlestick
					.setPriceMin(Double.parseDouble(line.substring(82, 95)) / 100.0);
			candlestick.setPriceAverage(Double.parseDouble(line.substring(95,
					108)) / 100.0);
			candlestick.setPriceClose(Double.parseDouble(line.substring(108,
					121)) / 100.0);

			candlestick.setTotalTrades(Integer.parseInt(line
					.substring(147, 152)));
			candlestick.setTotalQuantityStockTraded(Long.parseLong(line
					.substring(152, 170)));
			candlestick.setTotalVolume(new BigDecimal(Double.parseDouble(line
					.substring(170, 188)) / 100.0));

			return candlestick;
		}

		return null;
	}

	protected File getFile(File folderName, String fileName) {
		File file = new File(folderName, fileName);

		if (!file.exists()) {
			file = new File(folderName, fileName.replace('_', '.'));

			if (!file.exists()) {
				int endIndex = fileName.lastIndexOf('.');
				fileName = fileName.substring(0, endIndex);
				file = new File(folderName, fileName);

				if (!file.exists()) {
					file = new File(folderName, fileName.replace('_', '.'));
				}
			}
		}

		return file;
	}

	public List<Candlestick> read(File file) throws Exception {
		return read(file, 0);
	}

	public List<Candlestick> read(URL url) throws Exception {
		return read(url, 0);
	}

	public List<Candlestick> read(URL url, int bdiCode) throws Exception {
		File tempFolder = new File(System.getProperty("java.io.tmpdir"));
		String path = url.getPath();
		String fileName = path.substring(path.lastIndexOf("/") + 1,
				path.length());
		File targetFile = new File(tempFolder, fileName);

		FileOutputStream  fos = new FileOutputStream(targetFile);

		IOUtils.copy(url.openStream(), fos);

		fos.close();

		return read(targetFile, bdiCode);
	}

	public List<Candlestick> read(File file, int bdiCode) throws Exception {
		log.info("Starting reading : " + file);
		BufferedReader reader = null;

		File tempFolder = new File(System.getProperty("java.io.tmpdir"));

		String fileName = file.getName();

		fileName = fileName.replaceAll("ZIP", "TXT");
		new ZipFile(file).extractAll(tempFolder.getAbsolutePath());

		File txtFile = getFile(tempFolder, fileName);

		reader = new BufferedReader(new FileReader(txtFile));
		String line = reader.readLine();

		List<Candlestick> candlesticks = new LinkedList<>();
		while (line != null) {
			if (line.startsWith("01")) {
				Candlestick candlestick = convert(line, bdiCode);

				if (candlestick != null) {
					candlesticks.add(candlestick);
				}
			}

			line = reader.readLine();
		}
		reader.close();
		log.info("Candlestickes read: " + candlesticks.size());

		return candlesticks;
	}
}
