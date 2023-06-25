let onlineFilterRef = {}
let regOpenFilterRef = {}

let onlineFilterLabelRef = {}
let regOpenFilterLabelRef = {}
//let cityFilterRef = {}

let onlineFilterValue = false
let regOpenFilterValue = false

document.onload = () => {
    onlineFilterRef = document.getElementById('onlineFilter')
    regOpenFilterRef = document.getElementById('regOpenFilter')
}

function valueFilterChange(){
    cardWrapperRef=document.getElementById('cardWrapper')

    nameFilterRef=document.getElementById('searchInput')
    cityFilterRef=document.getElementById('cityFilter')
    categoryFilterRef=document.getElementById('categoryFilter')
    timeStartFilterRef=document.getElementById('timeStart')
    timeEndFilterRef=document.getElementById('timeEnd')
    typeFilterRef=document.getElementById('typeFilter')
    onlineFilter = document.getElementById('onlineFilter')
    regOpenFilterRef = document.getElementById('regOpenFilter')

    const myURL = new URL('http://127.0.0.1:8080/filtred-events')


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

    if(regOpenFilterRef.checked)
        myURL.searchParams.set('regOpened', 'true')

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

function onlineCheckboxClicked(value) {
    console.log(value.checked)

    onlineFilterLabelRef = document.getElementById('onlineFilterLabel')
    
    regOpenFilterRef = document.getElementById('regOpenFilter')

    cardWrapperRef=document.getElementById('cardWrapper')

    nameFilterRef=document.getElementById('searchInput')
    cityFilterRef=document.getElementById('cityFilter')
    categoryFilterRef=document.getElementById('categoryFilter')
    timeStartFilterRef=document.getElementById('timeStart')
    timeEndFilterRef=document.getElementById('timeEnd')
    typeFilterRef=document.getElementById('typeFilter')

    const myURL = new URL('http://127.0.0.1:8080/filtred-events')

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
    
        if(regOpenFilterRef.checked)
        myURL.searchParams.set('regOpened', 'true')


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

function regOpenedCheckboxClicked(value) {
    console.log(value.checked)

    onlineFilter = document.getElementById('onlineFilter')
    regOpenFilterLabelRef = document.getElementById('regOpenFilterLabel')

    cardWrapperRef=document.getElementById('cardWrapper')

    nameFilterRef=document.getElementById('searchInput')
    cityFilterRef=document.getElementById('cityFilter')
    categoryFilterRef=document.getElementById('categoryFilter')
    timeStartFilterRef=document.getElementById('timeStart')
    timeEndFilterRef=document.getElementById('timeEnd')
    typeFilterRef=document.getElementById('typeFilter')

    const myURL = new URL('http://127.0.0.1:8080/filtred-events')

    var name=nameFilterRef.value.toString()
    if(name!=null ||name!='')
        myURL.searchParams.set('name',name)

    if(cityFilterRef.selectedIndex!=0)
    myURL.searchParams.set('city', cityFilterRef.options[cityFilterRef.selectedIndex].text)

    if(categoryFilterRef.selectedIndex!=0)
        myURL.searchParams.set('category', categoryFilterRef.options[categoryFilterRef.selectedIndex].text)

    myURL.searchParams.set('timeStart', timeStartFilterRef.value)
    myURL.searchParams.set('timeEnd', timeEndFilterRef.value)

    if(typeFilterRef.selectedIndex!=0)
        myURL.searchParams.set('type', typeFilterRef.options[typeFilterRef.selectedIndex].text)

    if(onlineFilter.checked)
        myURL.searchParams.set('online', 'true')


    if (value.checked) {
        regOpenFilterLabelRef.style.backgroundColor = '#0C6AA5'
        regOpenFilterLabelRef.style.color = 'white'
        myURL.searchParams.set('regOpened', 'true')
    }
    else {
        regOpenFilterLabelRef.style.backgroundColor = 'white'
        regOpenFilterLabelRef.style.color = '#0C6AA5'
    }

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