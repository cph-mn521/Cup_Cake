function createTable(tableData)
{
    var table = document.createElement('table');
    var tableBody = document.createElement('tbody');

    tableData.forEach(function (rowData)
    {
        var row = document.createElement('tr');

        rowData.forEach(function (cellData)
        {
            var cell = document.createElement('td');
            cell.appendChild(document.createTextNode(cellData));
            row.appendChild(cell);
        });

        tableBody.appendChild(row);
    });

    table.appendChild(tableBody);
    document.body.appendChild(table);
}


function makeTableHTML(myArray)
{
    var result = "<table border=1>";
    for (var i = 0; i < myArray.length; i++)
    {
        result += "<tr>";
        for (var j = 0; j < myArray[i].length; j++)
        {
            result += "<td id=\"col" + i + "_row" + j + "\">" + myArray[i][j] + "</td>";
        }
        result += "</tr>";
    }
    result += "</table>";

    return result;
}


function makeNodeTable(myArray)
{
    var result = "<thead>\n";
    result += "<tr>\n";
    result += "th>Firstname</th>\n";
    result += "<th>Lastname</th>\n";
    result += "<th>Email</th>\n";
    result += "</tr>\n";
    result += "</thead>\n";
    result += "tbody>\n";
    for (var i = 0; i < myArray.length; i++)
    {
        result += "<tr>";
        for (var j = 0; j < myArray[i].length; j++)
        {
            result += "<td id=\"col" + i + "_row" + j + "\"></td>";
        }
        result += "</tr>";
    }
    result += "</tbody>";
}


function sortFirstColumn(a, b)
{
    if (a[0] === b[0])
    {
        return 0;
    } else
    {
        return (a[0] < b[0]) ? -1 : 1;
    }
}
function sortSecondColumn(a, b)
{
    if (a[1] === b[1])
    {
        return 0;
    } else
    {
        return (a[1] < b[1]) ? -1 : 1;
    }
}