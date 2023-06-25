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
    //document.getElementById('regdate').style.visibility = "hidden";
     document.getElementById('regdate').style.display = "none";
}

function regClick(value){

    if(value.checked){
        //document.getElementById('regdate').style.visibility = "visible";
        document.getElementById('regdate').style.display = "";
    }
    else{
    //document.getElementById('regdate').style.visibility = "hidden";
    document.getElementById('regdate').style.display = "none";
    }


}

function categoryChange(val){
categoryInputRef=document.getElementById('categoryInput')
categoryInputRef.value=val.options[val.selectedIndex].text
console.log(categoryInputRef.value)
}

function typeChange(val){
typeInputRef=document.getElementById('typeInput')
typeInputRef.value=val.options[val.selectedIndex].text
console.log(typeInputRef.value)
}
function placeChange(val){
placeInputRef=document.getElementById('placeInput')
placeInputRef.value=val.options[val.selectedIndex].text
console.log(placeInputRef.value)
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

