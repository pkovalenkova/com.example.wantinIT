let onlineFilterRef = {}
let regOpenFilterRef = {}

let onlineFilterLabelRef = {}
let regOpenFilterLabelRef = {}
//let cityFilterRef = {}

let onlineFilterValue = false
let regOpenFilterValue = false

document.onload = () => {
}

function valueFilterChangeArchive(){
    cardWrapperRef=document.getElementById('cardWrapper')

    nameFilterRef=document.getElementById('searchInput')
    cityFilterRef=document.getElementById('cityFilter')
    categoryFilterRef=document.getElementById('categoryFilter')
    timeStartFilterRef=document.getElementById('timeStart')
    timeEndFilterRef=document.getElementById('timeEnd')
    typeFilterRef=document.getElementById('typeFilter')
    onlineFilter = document.getElementById('onlineFilter')

    const myURL = new URL('http://127.0.0.1:8080/filtred-archive')


    var name=nameFilterRef.value.toString()
    if(name!=null ||name!='')
        myURL.searchParams.set('name',name)


    if(cityFilterRef.selectedIndex!=0)
        myURL.searchParams.set('city', cityFilterRef.options[cityFilterRef.selectedIndex].text)

    console.log(cityFilterRef.selectedIndex);
    console.log(cityFilterRef.options[cityFilterRef.selectedIndex].text.toString());

    if(categoryFilterRef.selectedIndex!=0)
        myURL.searchParams.set('category', categoryFilterRef.options[categoryFilterRef.selectedIndex].text)

    myURL.searchParams.set('timeStart', timeStartFilterRef.value.toString())
    myURL.searchParams.set('timeEnd', timeEndFilterRef.value.toString())

        if(typeFilterRef.selectedIndex!=0)
        myURL.searchParams.set('type', typeFilterRef.options[typeFilterRef.selectedIndex].text)

    if(onlineFilter.checked)
        myURL.searchParams.set('online', 'true')

    axios.get(myURL.toString())
        .then(function (response) {
          // обработка успешного запроса
          console.log(response.data.toString());
          cardWrapperRef.innerHTML=response.data.toString()
          //response =>response.json()
          //console.log(response);
        })
        .catch(function (error) {
          // обработка ошибки
          console.log(error);
        })
        .finally(function () {
          // выполняется всегда
        });

}

function followCheckboxClicked(value){
  console.log(value.checked)
  heartIconRef=document.getElementById('heartLabel')

  var str = window.location.pathname.toString()
  var eventId=str.slice(str.lastIndexOf('/')+1)
  console.log(eventId)

  const queryURL = new URL('http://127.0.0.1:8080/follow-script')

  if (value.checked) {
    queryURL.searchParams.set('value', 'true')
    
    //heartIconRef.innerHTML='<input type="checkbox" name="checkCheckbox" class="activities-checkbox" id="checkCheckbox" onclick="checkCheckboxClicked(this)" value="true"><img src="../static/heart2.svg", alt = "",style="height:45px"/>'
  }
  else{
    queryURL.searchParams.set('value', 'false')
    //heartIconRef.innerHTML='<input type="checkbox" name="checkCheckbox" class="activities-checkbox" id="checkCheckbox" onclick="checkCheckboxClicked(this)" value="false"><img src="../static/heart.svg", alt = "",style="height:45px"/>'
  }
  
  queryURL.searchParams.set('eventid', eventId)

  axios.get(queryURL.toString())
      .then(function (response) {
        // обработка успешного запроса
        console.log(response.data.toString());
        heartIconRef.innerHTML=response.data.toString()

      })
      .catch(function (error) {
        console.log(error);
      })
      .finally(function () {
      });
  
}

function takingPartCheckboxClicked(value){
  console.log(value.checked)
  checkLabelRef=document.getElementById('checkLabel')

  var str = window.location.pathname.toString()
  var eventId=str.slice(str.lastIndexOf('/')+1)
  console.log(eventId)

  const queryURL = new URL('http://127.0.0.1:8080/check-script')

  if (value.checked) {
    queryURL.searchParams.set('value', 'true')
    
    //heartIconRef.innerHTML='<input type="checkbox" name="checkCheckbox" class="activities-checkbox" id="checkCheckbox" onclick="checkCheckboxClicked(this)" value="true"><img src="../static/heart2.svg", alt = "",style="height:45px"/>'
  }
  else{
    queryURL.searchParams.set('value', 'false')
    //heartIconRef.innerHTML='<input type="checkbox" name="checkCheckbox" class="activities-checkbox" id="checkCheckbox" onclick="checkCheckboxClicked(this)" value="false"><img src="../static/heart.svg", alt = "",style="height:45px"/>'
  }
  queryURL.searchParams.set('eventid', eventId)

  axios.get(queryURL.toString())
      .then(function (response) {
        // обработка успешного запроса
        console.log(response.data.toString());
        checkLabelRef.innerHTML=response.data.toString()

      })
      .catch(function (error) {
        console.log(error);
      })
      .finally(function () {
      });
  
}

function onlineCheckboxClicked(value) {
    console.log(value.checked)

    onlineFilterLabelRef = document.getElementById('onlineFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    nameFilterRef=document.getElementById('searchInput')
    cityFilterRef=document.getElementById('cityFilter')
    categoryFilterRef=document.getElementById('categoryFilter')
    timeStartFilterRef=document.getElementById('timeStart')
    timeEndFilterRef=document.getElementById('timeEnd')
    typeFilterRef=document.getElementById('typeFilter')

    const myURL = new URL('http://127.0.0.1:8080/filtred-archive')

    //console.log(myURL.toString());
    var name=nameFilterRef.value.toString()
    if(name!=null ||name!='')
        myURL.searchParams.set('name',name)

    if(cityFilterRef.selectedIndex!=0)
        myURL.searchParams.set('city', cityFilterRef.options[cityFilterRef.selectedIndex].text)

    console.log(cityFilterRef.selectedIndex);
    console.log(cityFilterRef.options[cityFilterRef.selectedIndex].text.toString());
    
    if(categoryFilterRef.selectedIndex!=0)
        myURL.searchParams.set('category', categoryFilterRef.options[categoryFilterRef.selectedIndex].text)

    myURL.searchParams.set('timeStart', timeStartFilterRef.value)
    myURL.searchParams.set('timeEnd', timeEndFilterRef.value)

        if(typeFilterRef.selectedIndex!=0)
        myURL.searchParams.set('type', typeFilterRef.options[typeFilterRef.selectedIndex].text)

    if (value.checked) {
            onlineFilterLabelRef.style.backgroundColor = '#0C6AA5'
            onlineFilterLabelRef.style.color = 'white'
            myURL.searchParams.set('online', 'true')
    }
    else {
            onlineFilterLabelRef.style.backgroundColor = 'white'
            onlineFilterLabelRef.style.color = '#0C6AA5'
            myURL.searchParams.set('online', 'false')
    }
    console.log(myURL.toString());
    //location=myURL.toString();

   axios.get(myURL.toString())
      .then(function (response) {
        // обработка успешного запроса
        console.log(response.data.toString());
        cardWrapperRef.innerHTML=response.data.toString()
        //response =>response.json()
        //console.log(response);
      })
      .catch(function (error) {
        // обработка ошибки
        console.log(error);
      })
      .finally(function () {
        // выполняется всегда
      });
}