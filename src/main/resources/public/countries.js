const BASEURL = window.location.origin;
document.addEventListener('DOMContentLoaded', (event) => {
    loadCountries().then(showCountryDetails);
});

function loadCountries() {
    const promiseObj = new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', BASEURL + '/countries');
        xhr.onload = function() {
            if (xhr.status === 200) {
                const content = JSON.parse((xhr.responseText));
                //console.log(content);
                const countries = content.countries;
                if (countries.length > 0) {
                    addCountryList(countries);
                }
                resolve(xhr.status);
            }
            else {
                console.log("Something went wrong");
                reject(xhr.status);
            }
        };
        xhr.send();
    });

    return promiseObj;
}

function addCountryList(content) {
        const contentLength = content.length;
        for (let i = 0; i < contentLength; i++) {
            const country = content[i];
            const link = document.createElement("a");
            const textnode = document.createTextNode(country.name + " (" + country.country_code + ")");
            link.append(textnode);
            link.href = BASEURL + "/" + country.name;
            link.className = "list-group-item list-group-item-action country-selector";
            document.getElementById("countrylist").appendChild(link);
        }
}

function showCountryDetails() {
    const countrySelectors = document.querySelectorAll('.country-selector');
    countrySelectors.forEach(function (countrySelector) {
        countrySelector.addEventListener('click', function (event) {
            event.preventDefault();
            let name = event.target.href;
            name = name.split("/").pop();

            const xhr = new XMLHttpRequest();
            xhr.open('GET', BASEURL + '/countries/' + name);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    const country = JSON.parse((xhr.responseText));
                    console.log(country);
                    document.getElementById('countryname').innerHTML = country.name;
                    document.getElementById('countrycode').innerHTML = country.country_code;
                    document.getElementById('capital').innerHTML = country.capital;
                    document.getElementById('population').innerHTML = country.population;
                    document.getElementById('flag').src = country.flag_file_url;

                    document.getElementById('detail-wrapper').style.display = null;
                }
                else {
                    console.log("Something went wrong");
                }
            };
            xhr.send();
        });
    });
}