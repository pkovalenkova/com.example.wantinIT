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

    participantFilterLabelRef = document.getElementById('participantFilterLabel')
    participantFilterRef = document.getElementById('participantFilter')

    favFilterRef = document.getElementById('favFilter')
    spickerFilterRef = document.getElementById('spickerFilter')
    pastFilterRef = document.getElementById('pastFilter')

    participantFilterRef.value='true'
    favFilterRef.value='false'
    spickerFilterRef.value='false'
    pastFilterRef.value='false'

    participantFilterLabelRef.style.backgroundColor = '#0C6AA5'
    participantFilterLabelRef.style.color = 'white'

    cardWrapperRef=document.getElementById('cardWrapper')

    const queryURL=new URL('http://127.0.0.1:8080/profile-individual-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    queryURL.searchParams.set('participationtype', 'Участник')

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

function favCheckboxClicked(){

    favFilterRef = document.getElementById('favFilter')

    userEmailRef = document.getElementById('userEmail')    
    
    participantFilterRef = document.getElementById('participantFilter')
    spickerFilterRef = document.getElementById('spickerFilter')
    pastFilterRef = document.getElementById('pastFilter')
    
    favFilterLabelRef = document.getElementById('favFilterLabel')
    participantFilterLabelRef = document.getElementById('participantFilterLabel')
    spickerFilterLabelRef = document.getElementById('spickerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    const queryURL=new URL('http://127.0.0.1:8080/profile-individual-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    queryURL.searchParams.set('participationtype', 'Подписчик')


    favFilterRef.value='true'
    favFilterLabelRef.style.backgroundColor = '#0C6AA5'
    favFilterLabelRef.style.color = 'white'

    participantFilterRef.value='false'
    participantFilterLabelRef.style.backgroundColor = 'white'
    participantFilterLabelRef.style.color = '#0C6AA5'

    spickerFilterRef.value='false'
    spickerFilterLabelRef.style.backgroundColor = 'white'
    spickerFilterLabelRef.style.color = '#0C6AA5'

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

function participantCheckboxClicked(){
    participantFilterRef = document.getElementById('participantFilter')

    //if(!participantFilterRef.checked){
    userEmailRef = document.getElementById('userEmail') 
   
    favFilterRef = document.getElementById('favFilter')
    spickerFilterRef = document.getElementById('spickerFilter')
    pastFilterRef = document.getElementById('pastFilter')
    
    favFilterLabelRef = document.getElementById('favFilterLabel')
    participantFilterLabelRef = document.getElementById('participantFilterLabel')
    spickerFilterLabelRef = document.getElementById('spickerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    favFilterRef.value='false'
    favFilterLabelRef.style.backgroundColor = 'white'
    favFilterLabelRef.style.color = '#0C6AA5'

    participantFilterRef.value='true'
    participantFilterLabelRef.style.backgroundColor = '#0C6AA5'
    participantFilterLabelRef.style.color = 'white'

    spickerFilterRef.value='false'
    spickerFilterLabelRef.style.backgroundColor = 'white'
    spickerFilterLabelRef.style.color = '#0C6AA5'

    pastFilterRef.value='false'
    pastFilterLabelRef.style.backgroundColor = 'white'
    pastFilterLabelRef.style.color = '#0C6AA5'

    const queryURL=new URL('http://127.0.0.1:8080/profile-individual-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    queryURL.searchParams.set('participationtype', 'Участник')

    console.log(queryURL.toString());

    axios.get(queryURL.toString())
      .then(function (response) {
        console.log(response.data.toString())
        if(response.data.toString()!=null || response.data.toString()!=''){
            cardWrapperRef.innerHTML=response.data.toString()
          }
          else{
            cardWrapperRef.textContent='Подходящих мероприятий не найдено'
          }
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

function spickerCheckboxClicked(){

    spickerFilterRef = document.getElementById('spickerFilter')

    userEmailRef = document.getElementById('userEmail') 
   
    favFilterRef = document.getElementById('favFilter')
    participantFilterRef = document.getElementById('participantFilter')
    
    pastFilterRef = document.getElementById('pastFilter')
    
    favFilterLabelRef = document.getElementById('favFilterLabel')
    participantFilterLabelRef = document.getElementById('participantFilterLabel')
    spickerFilterLabelRef = document.getElementById('spickerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    favFilterRef.value='false'
    favFilterLabelRef.style.backgroundColor = 'white'
    favFilterLabelRef.style.color = '#0C6AA5'

    participantFilterRef.value='false'
    participantFilterLabelRef.style.backgroundColor = 'white'
    participantFilterLabelRef.style.color = '#0C6AA5'

    spickerFilterRef.value='true'
    spickerFilterLabelRef.style.backgroundColor = '#0C6AA5'
    spickerFilterLabelRef.style.color = 'white'

    pastFilterRef.value='false'
    pastFilterLabelRef.style.backgroundColor = 'white'
    pastFilterLabelRef.style.color = '#0C6AA5'

    const queryURL=new URL('http://127.0.0.1:8080/profile-individual-events')
    queryURL.searchParams.set('userEmail', userEmail.innerText)
    queryURL.searchParams.set('participationtype', 'Спикер')

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

function pastCheckboxClicked(){
    pastFilterRef = document.getElementById('pastFilter')
    userEmailRef = document.getElementById('userEmail') 
   
    favFilterRef = document.getElementById('favFilter')
    participantFilterRef = document.getElementById('participantFilter')
    spickerFilterRef = document.getElementById('spickerFilter')
    
    favFilterLabelRef = document.getElementById('favFilterLabel')
    participantFilterLabelRef = document.getElementById('participantFilterLabel')
    spickerFilterLabelRef = document.getElementById('spickerFilterLabel')
    pastFilterLabelRef = document.getElementById('pastFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    favFilterRef.value='false'
    favFilterLabelRef.style.backgroundColor = 'white'
    favFilterLabelRef.style.color = '#0C6AA5'

    participantFilterRef.value='false'
    participantFilterLabelRef.style.backgroundColor = 'white'
    participantFilterLabelRef.style.color = '#0C6AA5'

    spickerFilterRef.value='false'
    spickerFilterLabelRef.style.backgroundColor = 'white'
    spickerFilterLabelRef.style.color = '#0C6AA5'

    pastFilterRef.value='true'
    pastFilterLabelRef.style.backgroundColor = '#0C6AA5'
    pastFilterLabelRef.style.color = 'white'

    const queryURL=new URL('http://127.0.0.1:8080/profile-individualpastevents')
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

