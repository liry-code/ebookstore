function confirm_chk(str,loc_href)
{
	if(confirm(str)) {
	void(location.href=loc_href);
	}
}