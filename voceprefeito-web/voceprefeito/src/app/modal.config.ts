export function removeModal(){
    $('.modal').css("display","none")
    $('.modal').removeClass("in")
    $('.modal').css("padding-right","")
    $('.modal-backdrop').remove()
    $('body').removeClass("modal-open")
    $('body').css("padding-right","")
  }
