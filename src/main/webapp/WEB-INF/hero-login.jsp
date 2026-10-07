<section class="flex-grow-1 py-4 py-xl-5 hero">
    <div class="container h-100">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>Quests</h2>
                <p></p>
            </div>
        </div>
        <div class="row gy-4 row-cols-1 row-cols-md-2">

            <div class="col">
                <div class="d-flex flex-column">
                    <div class="text-center px-lg-4 py-4 py-lg-0">
                        <h4>Elf Quest</h4>
                        <p>Elf quest description</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/quest-page?quest=elfQuest&currentStep=start"
                       class="btn btn-primary btn-sm">Play</a>
                </div>
            </div>

            <div class="col">
                <div class="d-flex flex-column">
                    <div class="text-center px-lg-4 py-4 py-lg-0">
                        <h4>Ufo quest</h4>
                        <p>Quest about Ufo</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/quest-page?quest=ufoQuest&currentStep=start"
                       class="btn btn-primary btn-sm">Play</a>
                </div>
            </div>
            <div class="col">
                <div class="d-flex flex-column">
                    <div class="text-center px-lg-4 py-4 py-lg-0">
                        <h4>Quest 3</h4>
                        <p><br>quest 3 description</p>
                    </div>
                    <button class="btn btn-primary btn-sm" type="button">Play</button>
                </div>
            </div>
            <div class="col">
                <div class="d-flex flex-column">
                    <div class="text-center px-lg-4 py-4 py-lg-0">
                        <h4>Quest 4</h4>
                        <p><br>quest 4 description</p>
                    </div>
                    <button class="btn btn-primary btn-sm" type="button">Play</button>
                </div>
            </div>
        </div>
    </div>
</section>