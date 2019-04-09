/**
 * 
 */
package br.com.gleisonandrade.bancoapi.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public class DataUtil {
	private static final String FORMATO_PADRAO = "MM/dd/yyyy HH:mm";

	public static String dataFormatada(Date data) {
		Format formatter = new SimpleDateFormat(FORMATO_PADRAO);
		return formatter.format(data);
	}
}
