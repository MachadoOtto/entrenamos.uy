package tools;

import javax.servlet.http.HttpServletRequest;

import models.GestorWeb;

public class Parametrizer {
	public static String addParam(String url, String key,  String val) {
		if (!url.contains("?"))
			url+="?"+key+"="+val;
		else {
			String param = url.split("[?]")[1];
			String [] params = param.split("[&]");
			boolean f2=false;
			for (int i=0;i<params.length;i++)
				if (params[i].split("=")[0].equals(key)) {
					String [] vv = params[i].split("=")[1].split(", ");
					boolean f=false;
					for (int j=0;j<vv.length;j++)
						if (vv[j].equals(val)) {
							f = true;
							break;
						}
					if (!f)
						params[i]+=", "+val;
					f2 = true;
					break;
				}
			if (!f2) {
				param += "&"+key+"="+val;
				url=url.split("[?]")[0]+"?"+param;
			}
			else {
				url=url.split("[?]")[0]+"?"+String.join("&",  params);
			}
		}
		return url;
	}
	
	public static String remParam(String url, String key,  String val) {
		String [] sp = url.split("[?]");
		if (sp.length>1) {
			String [] params =sp[1].split("[&]");
			for (int i=0;i<params.length;i++) {
				if (params[i].split("=")[0].equals(key)) {
					String [] vv = params[i].split("=")[1].split(", ");
					if (vv.length>1) {
						String r = "";
						for (int j=0;j<vv.length;j++)
							if (!(vv[j].equals(val)))
								r = r+vv[j]+", ";
						if (r.length()>0) {
							r = r.substring(0,  r.length()-1);
							params[i]=params[i].split("=")[0]+"="+r;
						}
						else
							params[i]="";
					}
					else if (vv[0].equals(val))
							params[i]="";
					break;
				}
			}
			String pp = "";
			for (int i=0;i<params.length;i++)
				if (!(params[i].equals("")))
					pp+=params[i]+"&";
			if (pp.length()>0) {
				pp = pp.substring(0, pp.length()-1);
				url= sp[0]+"?"+pp;
			}
			else
				url=sp[0];
				
		}
		return url;
	}

	public static void loadStdRequests(HttpServletRequest req) {
		req.setAttribute("stdInstituciones",  GestorWeb.getIADC().obtenerInstituciones());
		req.setAttribute("stdCategorias",  GestorWeb.getIADC().obtenerCategorias());
	}
	
	
	/* Funcion para testear 
	public static void main() {
		String t = "www.test.com";
		t=addParam(t, "e", "2");
		System.out.println(t);
		t=addParam(t, "e", "1");
		System.out.println(t);
		t=addParam(t, "f", "3");
		System.out.println(t);
		t=addParam(t, "e", "1");
		System.out.println(t);
		t=addParam(t, "x", "1");
		System.out.println(t);
		t=addParam(t, "f", "1");
		System.out.println(t);
		t=addParam(t, "f", "3");
		System.out.println(t);
		
		t=remParam(t, "e", "2");
		System.out.println(t);
		t=remParam(t, "e", "1");
		System.out.println(t);
		t=remParam(t, "f", "3");
		System.out.println(t);
		t=remParam(t, "e", "1");
		System.out.println(t);
		t=remParam(t, "x", "1");
		System.out.println(t);
		t=remParam(t, "f", "1");
		System.out.println(t);
		t=remParam(t, "f", "3");
		System.out.println(t);
	} */
}
