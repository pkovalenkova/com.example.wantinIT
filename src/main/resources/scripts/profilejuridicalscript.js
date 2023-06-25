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
    userEmailRef = document.getElementById('userEmail')

    organizerFilterLabelRef = document.getElementById('organizerFilterLabel')
    organizerFilterRef = document.getElementById('organizerFilter')

    partnerFilterRef = document.getElementById('partnerFilter')
    pastFilterRef = document.getElementById('pastFilter')

    incomingFilterLabelRef = document.getElementById('incomingFilterLabel')
    incomingFilterRef = document.getElementById('incomingFilter')

    organizerFilterRef.value='true'
    partnerFilterRef.value='false'
    pastFilterRef.value='false'

    organizerFilterLabelRef.style.backgroundColor = '#0C6AA5'
    organizerFilterLabelRef.style.color = 'white'

    incomingFilterLabelRef.style.backgroundColor = '#0C6AA5'
    incomingFilterLabelRef.style.color = 'white'

    cardWrapperRef=document.getElementById('cardWrapper')

    const queryURL=new URL('http://127.0.0.1:8080/profile-juridical-events')
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

    eventRequestWrapperRef=document.getElementById('eventRequestWrapper')
    const secondQueryURL=new URL('http://127.0.0.1:8080/profile-juridical-eventsrequests')
    secondQueryURL.searchParams.set('userEmail', userEmail.innerText)
    console.log(secondQueryURL.toString());

    axios.get(secondQueryURL.toString())
          .then(function (response) {
             eventRequestWrapperRef.innerHTML=response.data.toString()
          })
          .catch(function (error) {
            // обработка ошибки
            console.log(error);
          })
          .finally(function () {
            // выполняется всегда
          });
}

function partnerCheckboxClicked(){

    partnerFilterRef = document.getElementById('partnerFilter')

    userEmailRef = document.getElementById('userEmail')    
    
    organizerFilterRef = document.getElementById('organizerFilter')
    pastFilterRef = document.getElementById('pastFilter')
    
    partnerFilterLabelRef = document.getElementById('partnerFilterLabel')
    spickerFilterLabelRef = document.getElementById('spickerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    const queryURL=new URL('http://127.0.0.1:8080/profile-individual-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    queryURL.searchParams.set('participationtype', 'Партнер')


    partnerFilterRef.value='true'
    partnerFilterLabelRef.style.backgroundColor = '#0C6AA5'
    partnerFilterLabelRef.style.color = 'white'

    organizerFilterRef.value='false'
    organizerFilterLabelRef.style.backgroundColor = 'white'
    organizerFilterLabelRef.style.color = '#0C6AA5'

    pastFilterRef.value='false'
    pastFilterLabelRef.style.backgroundColor = 'white'
    pastFilterLabelRef.style.color = '#0C6AA5'

    console.log(queryURL.toString())

    axios.get(queryURL.toString())
    .then(function (response) {
      console.log(response.data.toString());
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

function organizerCheckboxClicked(){
    organizerFilterRef = document.getElementById('organizerFilter')

    //if(!participantFilterRef.checked){
    userEmailRef = document.getElementById('userEmail') 
   
    partnerFilterRef = document.getElementById('partnerFilter')
    pastFilterRef = document.getElementById('pastFilter')
    
    partnerFilterLabelRef = document.getElementById('partnerFilterLabel')
    organizerFilterLabelRef = document.getElementById('organizerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    partnerFilterRef.value='false'
    partnerFilterLabelRef.style.backgroundColor = 'white'
    partnerFilterLabelRef.style.color = '#0C6AA5'

    organizerFilterRef.value='true'
    organizerFilterLabelRef.style.backgroundColor = '#0C6AA5'
    organizerFilterLabelRef.style.color = 'white'

    pastFilterRef.value='false'
    pastFilterLabelRef.style.backgroundColor = 'white'
    pastFilterLabelRef.style.color = '#0C6AA5'

    const queryURL=new URL('http://127.0.0.1:8080/profile-juridical-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    //queryURL.searchParams.set('participationtype', 'Организатор')

    console.log(queryURL.toString());

    axios.get(queryURL.toString())
      .then(function (response) {
        console.log(response.data.toString());
        cardWrapperRef.innerHTML=response.data.toString()
      })
      .catch(function (error) {
        // обработка ошибки
        console.log(error);
      })
      .finally(function () {
        // выполняется всегда
      });

   // }
}

function pastCheckboxClicked(){
    pastFilterRef = document.getElementById('pastFilter')
    userEmailRef = document.getElementById('userEmail') 
   
    partnerFilterRef = document.getElementById('partnerFilter')
    organizerFilterRef = document.getElementById('organizerFilter')
    
    partnerFilterLabelRef = document.getElementById('partnerFilterLabel')
    organizerFilterLabelRef = document.getElementById('organizerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    partnerFilterRef.value='false'
    partnerFilterLabelRef.style.backgroundColor = 'white'
    partnerFilterLabelRef.style.color = '#0C6AA5'

    organizerFilterRef.value='false'
    organizerFilterLabelRef.style.backgroundColor = 'white'
    organizerFilterLabelRef.style.color = '#0C6AA5'

    pastFilterRef.value='true'
    pastFilterLabelRef.style.backgroundColor = '#0C6AA5'
    pastFilterLabelRef.style.color = 'white'

    const queryURL=new URL('http://127.0.0.1:8080/profile-juridicalpastevents')
    queryURL.searchParams.set('userEmail', userEmail.innerText)

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

