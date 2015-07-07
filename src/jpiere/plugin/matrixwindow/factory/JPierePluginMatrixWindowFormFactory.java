/******************************************************************************
 * Product: JPiere(Localization Japan of iDempiere)   - Plugins               *
 * Plugin Name:Window X(Matrix Window)                                        *
 * Copyright (C) Hideaki Hagiwara All Rights Reserved.                        *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/******************************************************************************
 * JPiereはiDempiereの日本商慣習対応のディストリビューションであり、          *
 * プラグイン群です。                                                         *
 * このプログラムはGNU Gneral Public Licens Version2のもと公開しています。    *
 * このプログラムは自由に活用してもらう事を期待して公開していますが、         *
 * いかなる保証もしていません。                                               *
 * 著作権は萩原秀明(h.hagiwara@oss-erp.co.jp)が保有し、サポートサービスは     *
 * 株式会社オープンソース・イーアールピー・ソリューションズで                 *
 * 提供しています。サポートをご希望の際には、                                 *
 * 株式会社オープンソース・イーアールピー・ソリューションズまでご連絡下さい。 *
 * http://www.oss-erp.co.jp/                                                  *
 *****************************************************************************/
package jpiere.plugin.matrixwindow.factory;

import java.util.logging.Level;

import jpiere.plugin.matrixwindow.form.AbstractMatrixWindowForm;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.compiere.util.CLogger;


/**
 *  JPiere Plugin Matrix Window Form Factory
 *
 *  @author Hideaki Hagiwara（萩原 秀明:h.hagiwara@oss-erp.co.jp）
 *
 */
public class JPierePluginMatrixWindowFormFactory implements IFormFactory{

	private static final CLogger log = CLogger.getCLogger(JPierePluginMatrixWindowFormFactory.class);

	@Override
	public ADForm newFormInstance(String formName) {
		Object form = null;
	     if (formName.startsWith("jpiere.plugin.matrixwindow.form"))
	     {

	    	String matrixWindowValue = formName.substring("jpiere.plugin.matrixwindow.form.".length());

	       	ClassLoader loader = this.getClass().getClassLoader();
	       	Class<?> clazz = null;

			try
    		{
    			//	Create instance w/o parameters
        		clazz = loader.loadClass("jpiere.plugin.matrixwindow.form.JPiereMatrixWindow");
    		}
    		catch (Exception e)
    		{
    			if (log.isLoggable(Level.INFO))
    				log.log(Level.INFO, e.getLocalizedMessage(), e);
    		}


			if (clazz != null) {
				try
	    		{
	    			form = clazz.newInstance();
	    		}
	    		catch (Exception e)
	    		{
	    			if (log.isLoggable(Level.WARNING))
	    				log.log(Level.WARNING, e.getLocalizedMessage(), e);
	    		}
			}

		      if (form != null) {
					if (form instanceof AbstractMatrixWindowForm )
					{
						AbstractMatrixWindowForm  controller = (AbstractMatrixWindowForm) form;
						controller.createMatrixWindow(matrixWindowValue);
						ADForm adForm = controller.getForm();
						adForm.setICustomForm(controller);
						return adForm;
					}
		     }
	     }
	     return null;
	}


}
