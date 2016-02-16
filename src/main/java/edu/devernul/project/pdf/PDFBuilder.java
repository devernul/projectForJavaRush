package edu.devernul.project.pdf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;


import com.itextpdf.text.html.HtmlEncoder;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import edu.devernul.project.model.Task;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.tags.EscapeBodyTag;
import org.springframework.web.util.HtmlUtils;


public class PDFBuilder extends AbstractPdfView {

	public static final String TASK_NAME = "NAME";
	public static final String TASK_DATE = "DATE";
	public static final String TASK_DESCRIPTION = "DESCR";
	public static final String TASK_STATUS = "STATUS";
	public static final int NUM_COLUMNS = 4;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {



		List<Task> listTask = (List<Task>) model.get("tasks");
		Date date = new Date();
		doc.addTitle("Task list "+date);


		PdfPTable table = new PdfPTable(NUM_COLUMNS);



		PdfPCell cell2 = new PdfPCell(new Paragraph(TASK_NAME));
		PdfPCell cell3 = new PdfPCell(new Paragraph(TASK_DATE));
		PdfPCell cell4 = new PdfPCell(new Paragraph(TASK_STATUS));
		PdfPCell cell5 = new PdfPCell(new Paragraph(TASK_DESCRIPTION));


		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);

		for(Task task : listTask){

			 cell2 = new PdfPCell(new Paragraph(task.getName()));
			 cell3 = new PdfPCell(new Paragraph(String.valueOf(task.getDate().toString())));
			 cell4 = new PdfPCell(new Paragraph(task.getStatus().getName()));
			 cell5 = new PdfPCell(new Paragraph(task.getDescription()));


			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);


		}
		doc.add(table);
	}


}