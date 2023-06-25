let onlineFilterRef = {}
let regOpenFilterRef = {}

let onlineFilterLabelRef = {}
let regOpenFilterLabelRef = {}

let participantFilterLabelRef={}
let  participantFilterRef={}
let cardWrapperRef={}

//let cityFilterRef = {}

let onlineFilterValue = false
let regOpenFilterValue = false

window.onload = () => {
    cardWrapperRef=document.getElementById('cardWrapper')

    const queryURL=new URL('http://127.0.0.1:8080/profile-eventsrequests')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    //queryURL.searchParams.set('participationtype', 'Организатор')

    console.log(queryURL.toString());

    axios.get(queryURL.toString())
      .then(function (response) {
        cardWrapperRef.innerHTML=response.data.toString()
      })
      .catch(function (error) {
        // обработка ошибки
        console.log(error);
      })
      .finally(function () {
        // выполняется всегда
      });
}