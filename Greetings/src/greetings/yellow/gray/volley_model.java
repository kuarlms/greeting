package greetings.yellow.gray;
public class volley_model {
	
	
	/*{"image":"http:\/\/www.yellowandgray.com\/Greeting-Application\/admin\/images\/hike-700x350.jpg",
		"title":"Wishes","cate":"Happy New Year"}*/

	private String thumb; //url
	private String title;
	private String cate;
	
	public volley_model(){
		
	}
	

	public volley_model(String thumb, String title, String cate) {
		//constructor
		this.thumb = thumb;
		this.title = title;
		this.cate = cate;
		
		
		
	}


	public String get_imgurl() {
		return thumb;
	}

	public void set_imgurl(String thumb) {
		this.thumb = thumb;
	}

	public String get_title() {
		return title;
	}

	public void set_title(String title) {
		this.title = title;
	}

	public String get_cate() {
		return cate;
	}

	public void set_cate(String cate) {
		this.cate = cate;
	}

	

	
	}



