
import { PageLoading } from "../page-loader/page-loading";
import { Pagination } from "./ParamInterfaces";
import { TranslateService } from "@ngx-translate/core";
import { UtilsService } from "../../shared/services/utils.service";
import { CrudService } from "../../shared/services/crud.service";


/**
 * This Generic Pagination Component 
 * 1- extends GenericPagination<T>
 * 2- Inject  Your service then call super and Pass Your Injected Service
 * 3- set your params and it must extends PageParam  and write method and pass the following code
 * 4- const post = new PostParam(); // Your Custom Param That extends From PageParam
 * 5- post.page = this.page;
 * 6- this.setPageParam(post);
 * 7- this.loadPage();
 * 8- call your method in ngOnInit() method
 * 9- in your html component use results to iterate over each element That You Passed GenericPagination<T>
 * 10- in *ngFor="let post of results  | paginate: { id: 'server', itemsPerPage: 5, currentPage: page , totalItems: totalElements  } ; let i = index"
 * 11- <pagination-controls [responsive]="responsive" (pageChange)="onPageChange($event)" id="server"></pagination-controls> 
 */
export class GenericPagination<T> extends PageLoading{

    results: T[]; // return result of arrays

    page: number = 0;
    totalElements: number;
    paginationParam: Pagination;

    constructor(private service: CrudService<T, any>) {
     super(false);
    }

    /**
     * Load Page content
     */
    loadPage() {
        this.standby();
        this.service.findPage(this.paginationParam).subscribe(
            data => {
                this.totalElements = data.totalElements;
                this.results = data.content;
                this.ready();
            }
        );
    }


    /**
    * Action Implementation when click on Specific Page Number
    * @param page 
    */
    onPageChange(page: number) {

        this.page = page;

        this.paginationParam.setPage(page - 1);

        this.loadPage();

    }

    /**
     * 
     * @param paginationParam Page Paramters
     */
    setPageParam(paginationParam: Pagination) {
        this.paginationParam = paginationParam;
    }


}
