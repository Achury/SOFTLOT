var currentLink;
var tab_title;
var tab_id;

var srcIframe;

jQuery(function() {
	var defaultHeight = jQuery(window).height() - 29;

	//formClients:partnerDataTable:tb
	jQuery('[id$="partnerDataTable:n"] tr').live("dblclick", function() {
		currentLink = jQuery(this);

		var objLink = currentLink.children(":first-child").children(":first-child").children(":first-child");

		tab_id = objLink.attr("rel");
		tab_title = objLink.attr("title");

		srcIframe = 'client.jsp?status=existe';

		addTab(tab_id, tab_title);
		return false; //Evitar que el navegador siga el link
	});
	
	//Permite crear el nuevo cliente
	jQuery('a[id="formClients:linkNew"]').live("click", function() {
		
		tab_id = "New_Client";
		tab_title = "New Client";
		
		srcIframe = "client.jsp?status=nuevo";

		addTab(tab_id, tab_title);
		return false; //Evitar que el navegador siga el link
	});

	// tabs init with a custom tab template and an "add" callback filling in the content
	var tmp = jQuery("#tabs");
	var ttabs = tmp.tabs({
		tabTemplate: "<li><a href='\#{href}'>\#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
		add: function( event, ui ) {
			jQuery( ui.panel ).append(
				'<iframe id = "iframe_' + tab_title + '" width = "100%" height = "' + defaultHeight + 'px" frameborder = "0" src="' + srcIframe + '"></iframe>'
			);
		}
	});

	// actual addTab function: adds new tab using the title input from the form above
	function addTab(tab_id, tab_title) {
		var idTabContent = "#tabs-" + tab_id;

		//Si no existe
		if( !(jQuery(idTabContent).length > 0) ) {
			ttabs.tabs( "add",idTabContent, tab_title );
			var obj = jQuery("a[href='" + idTabContent + "']");
			obj[0].click();
		} else {
			var obj = jQuery("a[href='" + idTabContent + "']");
			obj[0].click();
		}
		return true;
	}

	// close icon: removing the tab on click
	// note: closable tabs gonna be an option in the future - see http://dev.jqueryui.com/ticket/3924
	jQuery( "#tabs span.ui-icon-close" ).live( "click", function() {
		var link=jQuery(this).prev();
		var href=link.attr("href");
		var iframe=jQuery("iframe",href)[0];
		var iframeDocument = null;
		if (iframe.contentDocument) {
			iframeDocument = iframe.contentDocument;
		} else if (iframe.contentWindow) {
			// for IE 5.5, 6 and 7:
			iframeDocument = iframe.contentWindow.document;
		}
		var cerrar = confirm("Desea guardar la información?");
		if (cerrar) {
			var boton=jQuery('[id$="clientDetails:botonSave"]',iframeDocument);
			boton.trigger("click");
			//jQuery('input[id="formClient:clientDetails:botonSave"]').click();
		}
		var index = jQuery( "li", ttabs ).index( jQuery( this ).parent() );
		ttabs.tabs( "remove", index );
	});
	
	jQuery(document).ready(function(){
		jQuery("body, input, textarea").keypress(function(e){
	        if(e.target.name != "formClients:codeText1"){ //  si el evento enter se captura en el campo "formClients:codeText1" 
	        	if(e.which==13)search();
	        }
	    });
	});
});