package competencies.us.screen;

import org.cass.competency.EcFramework;
import org.stjs.javascript.Global;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.Map;
import org.stjs.javascript.functions.Callback0;
import org.stjs.javascript.functions.Callback1;

import com.eduworks.ec.framework.view.manager.ScreenManager;
import com.eduworks.ec.framework.browser.url.URLParams;

public class FrameworkEditScreen extends CassManagerScreen
{

	static String displayName = "frameworkEdit";

	static
	{
		ScreenManager.addStartupScreenCallback(new Callback0()
		{
			@Override
			public void $invoke()
			{
				if (Global.window.document.location.hash.startsWith("#" + displayName))
				{
					final Map<String, Object> urlParameters = JSObjectAdapter.$properties(URLParams.getParams());

					String id = (String) urlParameters.$get("id");
					if (id != null)
					{
						EcFramework.get(id, new Callback1<EcFramework>()
						{
							@Override
							public void $invoke(EcFramework data)
							{
								ScreenManager.replaceScreen(new FrameworkEditScreen(data), afterReload, urlParameters);

							}

						}, new Callback1<String>()
						{
							@Override
							public void $invoke(String p1)
							{
								ScreenManager.replaceScreen(new FrameworkSearchScreen(null, null, null),
										afterReload, urlParameters);
							}
						});

						ScreenManager.startupScreen = ScreenManager.LOADING_STARTUP_PAGE;
						return;
					}
					ScreenManager.startupScreen = new FrameworkEditScreen(null);

				}
			}
		});
	}

	Object data;

	public FrameworkEditScreen(Object data)
	{
		this.data = data;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public String getHtmlLocation()
	{
		return "partial/screen/frameworkEdit.html";
	}

}
